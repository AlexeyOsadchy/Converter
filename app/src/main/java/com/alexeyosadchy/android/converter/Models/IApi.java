package com.alexeyosadchy.android.converter.Models;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface IApi {
    @GET("Rates")
    Observable<List<Currency>> getCurrency(@Query("Periodicity") int currencyId);
}
