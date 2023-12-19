package com.example.backend.dao;

import com.example.backend.entities.Profile;

public class ProfileDAO extends GenericFilterDAO<Profile, Integer> {
    public ProfileDAO() {
        super(Profile.class);
    }
}
