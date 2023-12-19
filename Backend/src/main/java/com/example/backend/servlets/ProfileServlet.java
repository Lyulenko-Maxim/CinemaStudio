package com.example.backend.servlets;

import com.example.backend.entities.Profile;
import com.example.backend.services.ProfileService;
import com.example.backend.utils.ServletUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/profiles/*")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProfileService profileService = new ProfileService(req);
        List<Profile> profiles = profileService.getProfiles();
        ServletUtils.writeJsonResponse(resp, profiles);
    }
}


