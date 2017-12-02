package com.alexeyosadchy.android.converter.Models;

import java.util.List;

/**
 * Created by Алексей on 01-Dec-17.
 */

public interface IModel {
    void updateDbCurrency();
    Double getCurrencyRate(String curAbbreviation);
}
