package com.example.backend.services;

import com.example.backend.dao.PhotoDAO;
import com.example.backend.dao.ProfileDAO;
import com.example.backend.entities.Photo;
import com.example.backend.entities.Profile;

public class ProfileService {

    private final ProfileDAO profileDAO;

    public ProfileService(ProfileDAO profileDAO) {
        this.profileDAO = profileDAO;
    }

    public void findProfile(int id) {
        profileDAO.retreive(id);
    }

    public void addProfile(Profile profile) {
        profileDAO.create(profile);
    }

    public void deleteProfile(int id) {
        profileDAO.delete(id);
    }

    public void updateProfile(Profile profile) {
        profileDAO.update(profile);
    }
}
