package com.example.appwhere.api;

import com.example.appwhere.models.Login;
import com.example.appwhere.models.Merchant;
import com.example.appwhere.utilities.Utils;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PresenterMerchant implements Callback<Merchant>  {

    public ListenerMerchant listener;

    public void attempMerchant(){
        Retrofit retrofit = Utils.getMerchant();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Merchant> request = apiService.merchant();
        request.enqueue(this);
        if(listener != null){
            listener.showProgress();
        }
    }

    @Override
    public void onResponse(Call<Merchant> merchantCall, Response<Merchant> response) {
        if(response.isSuccessful()){
            Merchant login = response.body();
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
    public void onFailure(Call<Merchant> call, Throwable t) {
        if(listener != null){
            listener.hideProgress();
            listener.showErrorMessage(t.getMessage());
        }
    }

}
