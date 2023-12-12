package com.example.client;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailEditFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_edit, container, false);
        String[] type = new String[] {"Нет", "Среднее", "Высшее"};

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getActivity(), R.layout.drop_down_item, type);

        AutoCompleteTextView editTextFilledExposedDropdown =
                view.findViewById(R.id.filled_exposed_dropdown);
        editTextFilledExposedDropdown.setAdapter(adapter);

        EditText editBirthplace = view.findViewById(R.id.editBirthplace);
        EditText editExperience = view.findViewById(R.id.editExperience);
        EditText editEmail = view.findViewById(R.id.editEmail);
        EditText editInstitution = view.findViewById(R.id.editInstitution);
        EditText editDescription = view.findViewById(R.id.editDescription);
        TextView textname = view.findViewById(R.id.textViewName);
        NetworkService.getInstance()
                .getJSONApi()
                .getProfile(3)
                .enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(@NonNull Call<Profile> call, @NonNull Response<Profile> response) {

                        Profile profile = response.body();
                        textname.setText(profile.getName() + " " + profile.getSurname());
                        editBirthplace.setText(profile.getBirthplace());
                        editInstitution.setText(profile.getInstitution());

                        editDescription.setText(profile.getDescription());
                        editExperience.setText(profile.getExperience());
                        editEmail.setText(profile.getEmail());
                    }
                    @Override
                    public void onFailure(@NonNull Call<Profile> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        Log.d("MainActivity", t.getMessage());
                    }
                });

        ImageButton back = view.findViewById(R.id.imageButton3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_detailEditFragment_to_detailFragment);
            }
        });
        return view;
    }
}