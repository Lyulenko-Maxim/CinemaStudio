package com.example.client;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Photo {

    @SerializedName("image")
    @Expose
    private byte[] photo;

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public byte[] getPhoto() {
        return photo;
    }

    @SerializedName("id")
    @Expose
    private int id;

    public void setId(int id) {this.id = id;}

    public int getId() {return id;}

}
