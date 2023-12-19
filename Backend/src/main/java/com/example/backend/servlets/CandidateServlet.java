package com.example.backend.servlets;

import com.example.backend.entities.Profile;
import com.example.backend.services.ProfileService;
import com.example.backend.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/profiles/candidates/*" })
public class CandidateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProfileService profileService = new ProfileService(req);
        List<Profile> profiles = profileService.getCandidatesProfiles();
        ServletUtils.writeJsonResponse(resp, profiles);
    }
}
