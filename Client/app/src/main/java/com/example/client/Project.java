package com.example.client;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Project {

    @SerializedName("name")
    @Expose
    private String name;


    @SerializedName("id")
    @Expose
    private int id;

    public void setId(int id) {this.id = id;}

    public int getId() {return id;}

    public void setName(String name) {this.name = name;}

    public String getName() {
        return name;
    }

}
