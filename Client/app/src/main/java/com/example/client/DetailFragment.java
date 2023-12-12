package com.example.client;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_detail, container, false);
        ImageButton back = view.findViewById(R.id.imageButton);
        ImageButton button2 = view.findViewById(R.id.imageButton2);
        TextView birthplace = view.findViewById(R.id.birthplace);
        TextView institution = view.findViewById(R.id.institution);
        TextView education = view.findViewById(R.id.education);
        TextView description = view.findViewById(R.id.description);
        NetworkService.getInstance()
                .getJSONApi()
                .getProfile(3)
                .enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(@NonNull Call<Profile> call, @NonNull Response<Profile> response) {

                        Profile profile = response.body();
                        birthplace.setText(profile.getBirthplace());
                        institution.setText(profile.getBirthplace());
                        education.setText(profile.getEducation());
                        description.setText(profile.getDescription());
                    }
                    @Override
                    public void onFailure(@NonNull Call<Profile> call, @NonNull Throwable t) {
                        birthplace.setText("Net");
                        t.printStackTrace();
                        Log.d("MainActivity", t.getMessage());
                    }
                });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_detailFragment_to_mainFragment);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_detailFragment_to_detailEditFragment);
            }
        });

        return view;
    }
}