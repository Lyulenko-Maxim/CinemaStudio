package com.example.client;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;


    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
