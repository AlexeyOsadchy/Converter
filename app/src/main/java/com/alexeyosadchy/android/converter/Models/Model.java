package com.alexeyosadchy.android.converter.Models;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.alexeyosadchy.android.converter.Application;
import com.alexeyosadchy.android.converter.Presenter.IMainActivityPresenter;
import com.alexeyosadchy.android.converter.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.Query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model implements IModel {
    private IMainActivityPresenter mPresenter;
    private CurrencyDao mCurrencyDb;
    private IApi mApi;

    public Model(IMainActivityPresenter presenter) {
        mPresenter = presenter;
        mApi = createRetrofitApi();
        mCurrencyDb = createDb();
        if (mCurrencyDb.count() == 0) initializationDb();
    }

    @Override
    public void updateDbCurrency() {
        getCurrency();
    }

    @Override
    public Double getCurrencyRate(String curAbbreviation) {
        Query<Currency> currencyQuery = mCurrencyDb.queryBuilder().where(
                CurrencyDao.Properties.CurAbbreviation.eq(curAbbreviation)).build();

        return currencyQuery.list().get(0).getCurOfficialRate() / currencyQuery.list().get(0).getCurScale();
    }

    private void getCurrency() {
        if (hasConnection(Application.getContext())) {
            mApi.getCurrency(0).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(listCurrency -> {
                for (Currency c : listCurrency) mCurrencyDb.update(c);
                mPresenter.onCompleted();
            }, exception -> mPresenter.onError(exception));
        } else {
            mPresenter.networkUnavailable();
        }
    }

    private IApi createRetrofitApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.nbrb.by/API/ExRates/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(IApi.class);
    }

    private void initializationDb() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(Application.getContext().
                            getResources().openRawResource(R.raw.initdb)))) {
                String s;
                while ((s = in.readLine()) != null) {
                    stringBuilder.append(s);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        JsonArray jsonArray = gson.fromJson(stringBuilder.toString(), JsonArray.class);
        for (int i = 0; i < jsonArray.size(); i++) {
            mCurrencyDb.insert(gson.fromJson(jsonArray.get(i), Currency.class));
        }
    }

    private CurrencyDao createDb(){
        DaoSession daoSession;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(Application.getContext(), "currency-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        return daoSession.getCurrencyDao() ;
    }

    private boolean hasConnection(final Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
