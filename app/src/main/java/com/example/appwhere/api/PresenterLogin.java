package com.example.appwhere.api;

import com.example.appwhere.models.Login;
import com.example.appwhere.utilities.Utils;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PresenterLogin implements Callback<Login> {

    public ListenerLogin listener;

    public void attempLogin(Map<String, String> params){
        Retrofit retrofit = Utils.getClient();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Login> request = apiService.login(params);
        request.enqueue(this);
        if(listener != null){
            listener.showProgress();
        }
    }

    @Override
    public void onResponse(Call<Login> call, Response<Login> response) {
        if(response.isSuccessful()){
            Login login = response.body();
            if(login != null) {
                if(listener != null) {
                    listener.successEntry(login);
                }
            } else {
                if(listener != null){
                    listener.showErrorMessage("Successful is failed");
                }
            }
        } else {
            if(listener != null){
                listener.showErrorMessage("Error");
            }
        }
        if(listener != null){
            listener.hideProgress();
        }
    }

    @Override
    public void onFailure(Call<Login> call, Throwable t) {
        if(listener != null){
            listener.hideProgress();
            listener.showErrorMessage(t.getMessage());
        }
    }
}
