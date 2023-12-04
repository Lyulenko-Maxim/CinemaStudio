package com.example.client;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        VideoView videoview = view.findViewById(R.id.videoview);
        String videoPath = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.vid;
        Uri uri = Uri.parse(videoPath);
        videoview.setVideoURI(uri);

        MediaController mediaController = new MediaController(getActivity());
        videoview.setMediaController(mediaController);
        mediaController.setAnchorView(videoview);

        RecyclerView rv = view.findViewById(R.id.RecView);
        List<Project> projects = Project.getPro().subList(0,2);
        ProjectAdapter adapter = new ProjectAdapter(getActivity(),projects);
        rv.setAdapter(adapter);

        ImageButton buttonEdit = view.findViewById(R.id.imageButton2);
        Button buttonMore = view.findViewById(R.id.button3);
        Button buttonProjects = view.findViewById(R.id.button4);
        Button buttonPhotos = view.findViewById(R.id.button5);
        Button buttonReviews = view.findViewById(R.id.button6);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_editMainFragment);
            }
        });
       buttonMore.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_detailFragment);
           }
       });
        buttonProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_projectsFragment);
            }
        });
        buttonPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_photosFragment);
            }
        });
       buttonReviews.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_reviewFragment2);
           }
       });
        return view;
    }
}