package com.example.client;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Project {

    @SerializedName("name")
    @Expose
    private String name;


    public Project (String name) {
        this.name = name;

    }

    public void setName(String name) {this.name = name;}

    public String getName() {
        return name;
    }



    @NonNull
    @Override
    public String toString() {
        return "name: " + getName();
    }
}
