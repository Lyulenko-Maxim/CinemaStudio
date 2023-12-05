package com.example.client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class PhotosFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photos, container, false);
        RecyclerView rv = view.findViewById(R.id.RecView);
        List<Photo> photos = Photo.getPhotos();
        PhotoAdapter adapter = new PhotoAdapter(getActivity(),photos);
        rv.setAdapter(adapter);
        ImageButton back = view.findViewById(R.id.imageButton3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_photosFragment_to_mainFragment);
            }
        });
        return view;
    }
}