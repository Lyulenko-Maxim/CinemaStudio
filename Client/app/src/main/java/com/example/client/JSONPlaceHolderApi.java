package com.example.client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {

    @GET("profiles/{id}")
    public Call<Profile> getProfile(@Path("id") int id);


    @GET("projects/{id}")
    public Call<List<Project>> getProject(@Path("id") int id);

    @GET("photos/{id}")
    public Call<List<Photo>> getPhoto(@Path("id") int id);

    @GET("reviews/{id}")
    public Call<List<Review>> getReview(@Path("id") int id);
}
