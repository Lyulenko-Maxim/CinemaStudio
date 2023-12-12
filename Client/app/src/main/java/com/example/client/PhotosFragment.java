package com.example.client;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PhotosFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photos, container, false);
        RecyclerView rv = view.findViewById(R.id.RecView);
        /*
        List<Photo> photos = Photo.getPhotos();
        PhotoAdapter adapter = new PhotoAdapter(getActivity(),photos);
        rv.setAdapter(adapter);*/
        ImageButton back = view.findViewById(R.id.imageButton3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_photosFragment_to_mainFragment);
            }
        });

        NetworkService.getInstance()
                .getJSONApi()
                .getPhoto(3)
                .enqueue(new Callback<List<Photo>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Photo>> call, @NonNull Response<List<Photo>> response) {

                        List<Photo> photos = response.body();
                        PhotoAdapter adapter = new PhotoAdapter(getActivity(),photos);
                        rv.setAdapter(adapter);
                    }
                    @Override
                    public void onFailure(@NonNull Call<List<Photo>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        Log.d("MainActivity", t.getMessage());
                    }
                });

        return view;
    }
}