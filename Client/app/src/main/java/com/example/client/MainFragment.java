package com.example.client;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        /*List<Project> projects = Project.getPro().subList(0,2);
        ProjectAdapter adapter = new ProjectAdapter(getActivity(),projects);
        rv.setAdapter(adapter);*/

        RecyclerView reviewRec = view.findViewById(R.id.reviewRec);
        /*
        List<Review> reviews = Review.getReviews().subList(0,1);
        ReviewAdapter adapterReview = new ReviewAdapter(getActivity(),reviews);
        reviewRec.setAdapter(adapterReview);*/

        RecyclerView photoRec = view.findViewById(R.id.RecyclerPhoto);
        /*
        List<Photo> photos = Photo.getPhotos().subList(0,3);
        PhotoAdapter photoReview = new PhotoAdapter(getActivity(),photos);
        photoRec.setAdapter(photoReview);*/

        ImageButton buttonEdit = view.findViewById(R.id.imageButton2);
        Button buttonMore = view.findViewById(R.id.button3);
        Button buttonProjects = view.findViewById(R.id.buttonProject);
        Button buttonPhotos = view.findViewById(R.id.buttonPhoto);
        Button buttonReviews = view.findViewById(R.id.buttonReviews);
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
        ImageView ava = view.findViewById(R.id.avatar);
        NetworkService.getInstance()
                .getJSONApi()
                .getProfile(3)
                .enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(@NonNull Call<Profile> call, @NonNull Response<Profile> response) {

                        Profile profile = response.body();
                        byte[] photo = profile.getAvatar();
                        if (photo != null) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
                            ava.setImageBitmap(bitmap);
                        }
                        List<Project> projects = null;
                        List<Photo> photos = null;
                        if (profile.getProjects().size()>2) {
                            projects = profile.getProjects().subList(0, 2);
                        }
                        else {
                            projects = profile.getProjects();
                        }
                        ProjectAdapter adapter = new ProjectAdapter(getActivity(),projects);
                        rv.setAdapter(adapter);
                        if (profile.getPhotos().size()>3) {
                            photos = profile.getPhotos().subList(0,3);
                        }
                        else {
                            photos = profile.getPhotos();
                        }
                        PhotoAdapter photoAdapter = new PhotoAdapter(getActivity(),photos);
                        photoRec.setAdapter(photoAdapter);
                        buttonPhotos.setText(profile.getPhotos().size() + "  ⟶");
                        buttonProjects.setText(profile.getProjects().size() + "  ⟶");
                    }


                    @Override
                    public void onFailure(@NonNull Call<Profile> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        Log.d("MainActivity", t.getMessage());
                    }
                });

        NetworkService.getInstance()
                .getJSONApi()
                .getReview(3)
                .enqueue(new Callback<List<Review>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Review>> call, @NonNull Response<List<Review>> response) {

                        List<Review> reviews = response.body();
                        buttonReviews.setText(reviews.size() + "  ⟶");
                        if (reviews.size()>1) {
                            reviews = reviews.subList(0,1);
                        }
                        ReviewAdapter adapter = new ReviewAdapter(getActivity(), reviews);
                        reviewRec.setAdapter(adapter);
                    }
                    @Override
                    public void onFailure(@NonNull Call<List<Review>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        Log.d("MainActivity", t.getMessage());
                    }
                });
        return view;
    }
}