package com.example.client;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProjectAdd extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_project_add, container, false);
        ImageButton back = view.findViewById(R.id.imageButton3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_projectAdd_to_projectsFragment);
            }
        });

        Button buttonAdd = view.findViewById(R.id.buttonAdd);
        EditText projectname = view.findViewById(R.id.addProject);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Project project = new Project(projectname.getText().toString());
                NetworkService.getInstance()
                        .getJSONApi()
                        .postProject(3,project)
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(getActivity(), "Data updated to API", Toast.LENGTH_SHORT).show();
                                    Navigation.findNavController(view).navigate(R.id.action_projectAdd_to_projectsFragment);
                                }
                                else {
                                    Toast.makeText(getActivity(), "Data not updated to API", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure
                                    (@NonNull Call<String> call, @NonNull Throwable t) {
                                Log.i("MainActivity", t.getMessage());
                                t.printStackTrace();
                            }
                        });
            }
        });
        return view;
    }
}