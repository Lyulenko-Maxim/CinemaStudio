package com.example.client;

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


public class ReviewFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        RecyclerView rv = view.findViewById(R.id.RecView);
        ImageButton back = view.findViewById(R.id.imageButton3);
        NetworkService.getInstance()
                .getJSONApi()
                .getReview(3)
                .enqueue(new Callback<List<Review>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Review>> call, @NonNull Response<List<Review>> response) {

                        List<Review> reviews = response.body();
                        ReviewAdapter adapter = new ReviewAdapter(getActivity(),reviews);
                        rv.setAdapter(adapter);
                    }
                    @Override
                    public void onFailure(@NonNull Call<List<Review>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        Log.d("MainActivity", t.getMessage());
                    }
                });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_reviewFragment_to_mainFragment);
            }
        });
        return view;
    }
}