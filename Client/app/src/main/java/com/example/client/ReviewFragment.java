package com.example.client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


public class ReviewFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        view.findViewById(R.id.RecView);
        /*
        List<Planet> planets = Planets.getPlanets();

        PlanetAdapter2.OnPlanetClickListener planetClickListener = new PlanetAdapter2.OnPlanetClickListener() {
            @Override
            public void onPlanetClick(Planet planet, int position) {
                model.select(position);
            }
        };
        PlanetAdapter2 adapter = new PlanetAdapter2(getActivity(),planets,planetClickListener);
        rv.setAdapter(adapter);*/
        ImageButton back = view.findViewById(R.id.imageButton3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_reviewFragment_to_mainFragment);
            }
        });
        return view;
    }
}