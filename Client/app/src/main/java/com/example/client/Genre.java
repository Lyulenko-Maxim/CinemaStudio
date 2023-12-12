package com.example.client;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Genre {
    @SerializedName("name")
    @Expose
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return name;
    }

    @SerializedName("id")
    @Expose
    private int id;

    public void setId(int id) {this.id = id;}

    public int getId() {return id;}
}
