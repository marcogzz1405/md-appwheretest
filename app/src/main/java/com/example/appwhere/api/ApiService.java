package com.example.appwhere.api;

import com.example.appwhere.models.Login;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiService {

    @GET("login")
    //@FormUrlEncoded
    Call<Login> login(@QueryMap Map<String, String> params);

}
