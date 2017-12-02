package com.alexeyosadchy.android.converter.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alexeyosadchy.android.converter.Presenter.IMainActivityPresenter;
import com.alexeyosadchy.android.converter.Presenter.MainActivityPresenter;
import com.alexeyosadchy.android.converter.R;
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class MainActivity extends AppCompatActivity implements IMainActivityView {
    private final String APP_SHARED_PREFERENCES = "settings";
    private final String DATE_LAST_UPDATE = "date";
    private IMainActivityPresenter mPresenter;
    private SharedPreferences mSharedPreferences;
    @BindView(R.id.updateInfoButton)
    Button updateInfoButton;

    @BindView(R.id.reverseButton)
    ImageButton swapButton;

    @BindView(R.id.clearButton)
    ImageButton clearButton;

    @BindView(R.id.currency_1)
    Spinner currency_1;

    @BindView(R.id.currency_2)
    Spinner currency_2;

    @BindView(R.id.textCurrency1)
    EditText text1;

    @BindView(R.id.textCurrency2)
    EditText text2;

    @BindView(R.id.textViewDate)
    TextView textViewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
    }

    private void initialization() {
        ButterKnife.bind(this);
        mPresenter = new MainActivityPresenter();
        mPresenter.attachView(this);
        String[] cities = {"USD", "PLN", "RUB", "UAH", "EUR", "AUD", "BGN", "UAH", "DKK", "USD", "EUR", "PLN",
                "IRR", "ISK", "JPY", "CAD", "CNY", "KWD", "MDL", "NZD", "NOK", "RUB",
                "XDR", "SGD", "KGS", "KZT", "TRY", "GBP", "CZK", "SEK", "CHF"};
        ArrayAdapter<String> adapterCurrency1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        ArrayAdapter<String> adapterCurrency2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        currency_1.setAdapter(adapterCurrency1);
        currency_2.setAdapter(adapterCurrency2);
        RxTextView.textChanges(text1).subscribe(action1 -> mPresenter.onChangeEditText());
        mSharedPreferences = getSharedPreferences(APP_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        if (mSharedPreferences.contains(DATE_LAST_UPDATE)) {
            textViewDate.setText(mSharedPreferences.getString(DATE_LAST_UPDATE, ""));
        } else {
            textViewDate.setText("Not updated");
        }
    }

    @Override
    public String getValueOfText1() {
        return text1.getText().toString();
    }

    @Override
    public void setRateEditText2(String rate) {
        text2.setText(rate);
    }

    @OnItemSelected(R.id.currency_1)
    public void onItemSelectedSpinner1() {
        mPresenter.onClickSpinner();
    }

    @OnItemSelected(R.id.currency_2)
    public void onItemSelectedSpinner2() {
        mPresenter.onClickSpinner();
    }

    @Override
    public String getSelectedItemSpinner1() {
        return (String) currency_1.getSelectedItem();
    }

    @Override
    public String getSelectedItemSpinner2() {
        return (String) currency_2.getSelectedItem();
    }

    @Override
    public void clearEditText() {
        text1.setText("");
        text2.setText("");
    }

    @Override
    public void swapSpinners() {
        int position = currency_1.getSelectedItemPosition();
        currency_1.setSelection(currency_2.getSelectedItemPosition());
        currency_2.setSelection(position);
    }

    @OnClick(R.id.updateInfoButton)
    void onClickUpdate() {
        mPresenter.onUpdateInfo();
    }

    @OnClick(R.id.clearButton)
    void onClickCleanButton() {
        mPresenter.onClickClearButton();
    }

    @OnClick(R.id.reverseButton)
    void onClickReverse() {
        swapSpinners();
        mPresenter.reverseCurrency();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void setDate(String date) {
        textViewDate.setText(date);
        mSharedPreferences.edit().putString(DATE_LAST_UPDATE, date).apply();
    }
}
