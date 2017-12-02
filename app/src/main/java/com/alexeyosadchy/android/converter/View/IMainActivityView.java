package com.alexeyosadchy.android.converter.View;

public interface IMainActivityView {
    String getSelectedItemSpinner1();
    String getSelectedItemSpinner2();
    void setRateEditText2(String rate);
    String getValueOfText1();
    void clearEditText();
    void swapSpinners();
    void showToast(String message);
    void setDate(String date);
}
