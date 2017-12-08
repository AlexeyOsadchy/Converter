package com.alexeyosadchy.android.converter.Presenter;

import com.alexeyosadchy.android.converter.View.IMainActivityView;

public interface IMainActivityPresenter {
    void attachView(IMainActivityView view);
    void detachView();
    void onUpdateInfo();
    void onClickSpinner();
    void reverseCurrency();
    void onChangeEditText();
    void onChangeEditText2();
    void onClickClearButton();
    void networkUnavailable();
    void onError(Throwable e);
    void onCompleted();
}
