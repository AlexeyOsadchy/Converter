package com.alexeyosadchy.android.converter.View;

public interface IMainActivityView {
    String getSelectedItemSpinner1();
    String getSelectedItemSpinner2();
    void setRateEditText2(String rate);
    void setRateEditText1(String rate);
    String getValueOfText1();
    String getValueOfText2();
    void clearEditText();
    void swapSpinners();
    void showToast(String message);
    void setDate(String date);
    int getSelectedEditText();
}
