package com.example.backend.servlets;

import com.example.backend.entities.Location;
import com.example.backend.services.LocationService;
import com.example.backend.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/locations/*"})
public class LocationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocationService locationService = new LocationService();
        List<Location> locations = locationService.getLocations();
        ServletUtils.writeJsonResponse(resp, locations);
    }
}
