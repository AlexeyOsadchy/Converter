package com.alexeyosadchy.android.converter.Presenter;


import com.alexeyosadchy.android.converter.Models.IModel;
import com.alexeyosadchy.android.converter.Models.Model;
import com.alexeyosadchy.android.converter.View.IMainActivityView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivityPresenter implements IMainActivityPresenter {
    private IModel mModel = new Model(this);
    private IMainActivityView mIView;

    @Override
    public void onClickSpinner() {
        onChangeEditText();
        chooseConverter();
    }

    @Override
    public void onChangeEditText() {
        Double currency_1 = mModel.getCurrencyRate(mIView.getSelectedItemSpinner1());
        Double currency_2 = mModel.getCurrencyRate(mIView.getSelectedItemSpinner2());
        if (mIView.getValueOfText1().isEmpty() || mIView.getValueOfText1().equals(".")) {
            mIView.setRateEditText2("");
        } else
            mIView.setRateEditText2(new DecimalFormat("######.##", new DecimalFormatSymbols(Locale.US))
                    .format((currency_1 / currency_2) * Double.valueOf(mIView.getValueOfText1())));
    }

    @Override
    public void onChangeEditText2() {
        Double currency_2 = mModel.getCurrencyRate(mIView.getSelectedItemSpinner1());
        Double currency_1 = mModel.getCurrencyRate(mIView.getSelectedItemSpinner2());
        if (mIView.getValueOfText2().isEmpty() || mIView.getValueOfText2().equals(".")) {
            mIView.setRateEditText1("");
        } else
            mIView.setRateEditText1(new DecimalFormat("######.##", new DecimalFormatSymbols(Locale.US))
                    .format((currency_1 / currency_2) * Double.valueOf(mIView.getValueOfText2())));
    }

    @Override
    public void onUpdateInfo() {
        mModel.updateDbCurrency();
    }

    @Override
    public void onClickClearButton() {
        mIView.clearEditText();
    }

    @Override
    public void reverseCurrency() {
        onChangeEditText();
    }

    @Override
    public void attachView(IMainActivityView view) {
        mIView = view;
    }

    @Override
    public void detachView() {
        mIView = null;
    }

    @Override
    public void onError(Throwable t) {
        mIView.showToast("Ошибка подключения к серверу");
    }

    @Override
    public void networkUnavailable() {
        mIView.showToast("Нет интернет соединения");
    }

    @Override
    public void onCompleted() {
        mIView.setDate("Last update: " + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        chooseConverter();
    }

    private void chooseConverter(){
        if (mIView.getSelectedEditText() == 1) {
            onChangeEditText();
        } else {
            onChangeEditText2();
        }
    }
}
