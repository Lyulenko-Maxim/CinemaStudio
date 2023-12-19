package com.example.backend.services;

import com.example.backend.dao.LocationDAO;
import com.example.backend.entities.Location;

import java.util.List;

public class LocationService {
    private final LocationDAO locationDAO = new LocationDAO();

    public List<Location> getLocations() {
        return locationDAO.list();
    }
}
