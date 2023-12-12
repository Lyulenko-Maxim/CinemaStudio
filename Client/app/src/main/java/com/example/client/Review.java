package com.example.client;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Review {

    @SerializedName("review")
    @Expose
    private String review;


    @SerializedName("sender")
    @Expose
    private Profile sender;


    public void setReview(String review) {
        this.review = review;
    }

    public String getReview() {
        return review;
    }

    public Profile getSender() {
        return sender;
    }

    public void setSender(Profile sender) {
        this.sender = sender;
    }

}
