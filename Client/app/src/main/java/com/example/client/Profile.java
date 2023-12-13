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


    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("genres")
    @Expose
    private Set<Genre> genres;

    @SerializedName("professions")
    @Expose
    private Set<Profession> professions;

    @SerializedName("user")
    @Expose
    private User user;


    public User getUser() {return  user;}
    public void setUser(User user) {this.user = user;}

    public void setGenres(Set<Genre> genres) {this.genres=genres;}

    public Set<Genre> getGenres() {return genres;}


    public void setProfessions(Set<Profession> professions) {this.professions=professions;}

    public Set<Profession> getProfessions() {return professions;}

    public String getName() {return name;}
    public String getSurname() {return surname;}

    public String getEmail() {return email;}

    public void setEmail(String email) { this.email = email; }

    public void setName(String name) {this.name = name;}

    public void setSurname(String surname) {this.surname= surname;}

    public List<Project> getProjects() {return projects;}

    public List<Photo> getPhotos() {return photos;}

    public void setPhotos(List<Photo> photos) {this.photos = photos;}

    public void setProjects(List<Project> projects) {this.projects = projects;}

    public String getBirthplace() {
      return birthplace;
    }

    public void setExperience(String experience) {this.experience=experience;}
    public void setEducation(String education) {this.education=education;}
    public void setInstitution(String institution) {this.institution=institution;}
    public void setDescription(String description) {this.description=description;}

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
