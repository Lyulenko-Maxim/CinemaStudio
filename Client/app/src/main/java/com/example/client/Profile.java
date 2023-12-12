package com.example.client;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;
import java.util.Set;


public class Profile {


    @SerializedName("avatar")
    @Expose
    private byte[] avatar;

    @SerializedName("birthplace")
    @Expose
    private String birthplace;

    @SerializedName("experience")
    @Expose
    private String experience;

    @SerializedName("education")
    @Expose
    private String education;


    @SerializedName("institution")
    @Expose
    private String institution;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("surname")
    @Expose
    private String surname;


    @SerializedName("projects")
    @Expose
    private List<Project> projects;

    @SerializedName("photos")
    @Expose
    private List<Photo> photos;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("id")
    @Expose
    private int id;


    public String getName() {return name;}
    public String getSurname() {return surname;}

    public void setName(String name) {this.name = name;}

    public void setSurname(String surname) {this.surname= surname;}

    public List<Project> getProjects() {return projects;}

    public List<Photo> getPhotos() {return photos;}

    public void setPhotos(List<Photo> photos) {this.photos = photos;}

    public void setProjects(List<Project> projects) {this.projects = projects;}

    public String getBirthplace() {
      return birthplace;
    }

    public String getExperience() {return experience;}

    public String getEducation() {return  education;}

    public String getInstitution() {return institution;}

    public String getDescription() {return  description;}

    public byte[] getAvatar() {return avatar;}

    public void setAvatar(byte[] avatar) {this.avatar = avatar;}
    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id =id;
    }
}
