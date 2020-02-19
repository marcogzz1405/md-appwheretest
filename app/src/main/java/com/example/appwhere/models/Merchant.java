package com.example.appwhere.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Merchant {

    public int status;
    public String description;
    public List<Merchants> merchants;

    class Merchants {

        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("merchantName")
        @Expose
        private String merchantName;

        @SerializedName("merchantAddress")
        @Expose
        private String merchantAddress;

        @SerializedName("merchantTelephone")
        @Expose
        private String merchantTelephone;

        @SerializedName("latitude")
        @Expose
        private double latitude;

        @SerializedName("longitude")
        @Expose
        private double longitude;

        @SerializedName("registrationDate")
        @Expose
        private String registrationDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public String getMerchantAddress() {
            return merchantAddress;
        }

        public void setMerchantAddress(String merchantAddress) {
            this.merchantAddress = merchantAddress;
        }

        public String getMerchantTelephone() {
            return merchantTelephone;
        }

        public void setMerchantTelephone(String merchantTelephone) {
            this.merchantTelephone = merchantTelephone;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getRegistrationDate() {
            return registrationDate;
        }

        public void setRegistrationDate(String registrationDate) {
            this.registrationDate = registrationDate;
        }
    }

}
