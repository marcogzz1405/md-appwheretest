package com.example.appwhere.utilities;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utils {

    public static Retrofit getClient() {
        return new Retrofit.Builder()
                .baseUrl(Settings.URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getMerchant() {
        return new Retrofit.Builder()
                .baseUrl(Settings.URL_MERCHANTT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
