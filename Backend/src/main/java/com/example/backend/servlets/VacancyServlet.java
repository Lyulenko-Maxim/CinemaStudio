package com.example.backend.servlets;

import com.example.backend.entities.Vacancy;
import com.example.backend.services.VacancyService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/vacancies/"})
public class VacancyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");

        List<Vacancy> vacancies = new ArrayList<>();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        String jsonResult = gson.toJson(vacancies);

        OutputStream out = resp.getOutputStream();
        out.write(jsonResult.getBytes());
        out.flush();
    }
}
