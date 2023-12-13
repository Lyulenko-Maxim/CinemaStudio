package com.example.client;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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


    @Headers("Content-Type: application/json")
    @PUT("profiles/{id}")
    public Call<String> updateProfile(@Path("id") int id, @Body Profile profile);


    @Headers("Content-Type: application/json")
    @POST("projects/{id}")
    public Call<String> postProject(@Path("id") int id, @Body Project project);
}
