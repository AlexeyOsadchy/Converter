package com.alexeyosadchy.android.converter.Presenter;

import com.alexeyosadchy.android.converter.Models.IModel;
import com.alexeyosadchy.android.converter.Models.Model;
import com.alexeyosadchy.android.converter.View.IMainActivityView;

import java.text.DecimalFormat;
import java.util.Date;

public class MainActivityPresenter implements IMainActivityPresenter {
    private IModel mModel = new Model(this);
    private IMainActivityView mIView;

    @Override
    public void onClickSpinner() {
        onChangeEditText();
    }

    @Override
    public void onChangeEditText() {
        Double currency_1 = mModel.getCurrencyRate(mIView.getSelectedItemSpinner1());
        Double currency_2 = mModel.getCurrencyRate(mIView.getSelectedItemSpinner2());
        if (mIView.getValueOfText1().isEmpty()) {
            mIView.setRateEditText2("");
        } else
            mIView.setRateEditText2(new DecimalFormat("###,###.##").
                    format((currency_1 / currency_2) * Double.valueOf(mIView.getValueOfText1())));
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
        StringBuilder str = new StringBuilder((new Date()).toString());
        str.delete(19, 29);
        mIView.setDate("Last update: " + str.toString());
    }
}
