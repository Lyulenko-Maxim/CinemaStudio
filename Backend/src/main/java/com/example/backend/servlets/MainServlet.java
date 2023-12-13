package com.example.backend.servlets;

import com.example.backend.dao.VacancyDAO;
import com.example.backend.entities.Position;
import com.example.backend.entities.Vacancy;
import com.example.backend.services.PositionService;
import com.example.backend.utils.HibernateUtil;
import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet(urlPatterns = "/*")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PositionService positionService = new PositionService();
        List<Position> positions = positionService.getPositions(req);
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        String jsonResult = gson.toJson(positions);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        OutputStream out = resp.getOutputStream();
        out.write(jsonResult.getBytes());
        out.flush();
    }
}
