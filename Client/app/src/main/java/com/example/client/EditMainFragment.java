package com.example.client;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.Collections;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditMainFragment extends Fragment {



    TextView textView;
    boolean[] selectedLanguage;
    ArrayList<Integer> langList = new ArrayList<>();
    String[] langArray = {"Актер", "Продюсер", "Сценарист", "Режисер", "Оператор", "Монтажер"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_main, container, false);
        ImageButton back = view.findViewById(R.id.imageButton3);
        EditText editName = view.findViewById(R.id.editName);
        EditText editDate = view.findViewById(R.id.editDate);
        EditText editPhone = view.findViewById(R.id.editPhone);
        ImageView ava = view.findViewById(R.id.imageView);

        textView = view.findViewById(R.id.textProfession);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                // set title
                builder.setTitle("Выберите профессию");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(langArray, selectedLanguage, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            langList.add(i);
                            // Sort array list
                            Collections.sort(langList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            langList.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < langList.size(); j++) {
                            // concat array value
                            stringBuilder.append(langArray[langList.get(j)]);
                            // check condition
                            if (j != langList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        textView.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedLanguage.length; j++) {
                            // remove all selection
                            selectedLanguage[j] = false;
                            // clear language list
                            langList.clear();
                            // clear text view value
                            textView.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });

        // initialize selected language array
        selectedLanguage = new boolean[langArray.length];
        Profile profile= new Profile();
        NetworkService.getInstance()
                .getJSONApi()
                .getProfile(3)
                .enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(@NonNull Call<Profile> call, @NonNull Response<Profile> response) {

                        Profile profile = response.body();
                        editName.setText(profile.getName() + " " + profile.getSurname());
                        editPhone.setText(profile.getUser().getPhoneNumber());
                        profile.setEmail(profile.getEmail());
                        profile.setPhotos(profile.getPhotos());
                        profile.setProjects(profile.getProjects());
                        profile.setAvatar(profile.getAvatar());
                        profile.setBirthplace(profile.getBirthplace());
                        profile.setName(profile.getSurname());
                        profile.setUser(profile.getUser());
                        profile.setSurname(profile.getSurname());
                        profile.setId(profile.getId());
                        byte[] photo = profile.getAvatar();
                        if (photo != null) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
                            ava.setImageBitmap(bitmap);
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<Profile> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        Log.d("MainActivity", t.getMessage());
                    }
                });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_editMainFragment_to_mainFragment);
            }
        });

        ImageButton Save = view.findViewById(R.id.imageButton4);
        Save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String namestr = editName.getText().toString();
                String[] name = namestr.split(" ");
                profile.setName(name[0]);
                profile.setSurname(name[1]);
                NetworkService.getInstance()
                        .getJSONApi()
                        .updateProfile(3,profile)
                        .enqueue(new Callback<Profile>() {
                            @Override
                            public void onResponse(@NonNull Call<Profile> call, @NonNull Response<Profile> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(getActivity(), "Data updated to API", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(getActivity(), "Data not updated to API", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure
                                    (@NonNull Call<Profile> call, @NonNull Throwable t) {
                                //  textCity.append("Error occurred while getting request!");
                                Log.i("MainActivity", t.getMessage());
                                t.printStackTrace();
                            }
                        });
            }
        });
        return view;
    }
}