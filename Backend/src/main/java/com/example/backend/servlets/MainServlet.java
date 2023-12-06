package com.example.backend.servlets;

import com.example.backend.dao.VacancyDAO;
import com.example.backend.entities.Vacancy;
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
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
//            resp.getWriter().println("Hello world");
        }
        VacancyDAO vacancyDAO = new VacancyDAO(sessionFactory);
        List<Vacancy> vacancies = vacancyDAO.list();

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        String jsonResult = gson.toJson(vacancies);

        OutputStream out = resp.getOutputStream();
        out.write(jsonResult.getBytes());
        out.flush();
    }
}
