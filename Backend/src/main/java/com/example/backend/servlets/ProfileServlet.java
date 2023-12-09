package com.example.backend.servlets;

import com.example.backend.dao.ProfileDAO;
import com.example.backend.entities.Profile;
import com.example.backend.utils.HibernateUtil;
import com.google.gson.Gson;
import org.hibernate.SessionFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;

@WebServlet("/profile/*")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String pathInfo = req.getServletPath();
        if (pathInfo.matches("\\/[0-9]+\\/{0,1}")) {
            String numberString = pathInfo.replace("/", "");
            int number = Integer.parseInt(numberString);
            ProfileDAO profileDAO = new ProfileDAO();
            String json = this.toJson(profileDAO.retreive(number));
            if (json == null) {
                this.outputResponse(resp, "Профиль не найден.", 404);
            }
            else {
                this.outputResponse(resp,json,200);
            }
        } else {
            this.outputResponse(resp, "Запрос сформулирован неверно.", 500);
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws  IOException{
        String pathInfo = req.getPathInfo();

        if (pathInfo.matches("\\/[0-9]+\\/{0,1}")) {
                String numberString = pathInfo.replace("/", "");
                long number = Integer.parseInt(numberString);
                int user_id = Integer.parseInt(req.getParameter("user_id"));
                int profession_id = Integer.parseInt(req.getParameter("profession_id"));
                int genre_id = Integer.parseInt(req.getParameter("genre_id"));
                Date birthdate = Date.valueOf(req.getParameter("birth_date"));
                byte[] avatar = req.getParameter("avatar").getBytes();
                String birthplace = req.getParameter("birthplace");
                String experience = req.getParameter("experience");
                String education = req.getParameter("education");
                String institution = req.getParameter("institution");
                String description = req.getParameter("description");
                ProfileDAO profileDAO = new ProfileDAO();
                Profile profile = new Profile();
                profile.setId(number);
                profile.setBirthdate(birthdate);
                profile.setBirthplace(birthplace);
                profile.setExperience(experience);
                profile.setEducation(education);
                profile.setInstitution(institution);
                profile.setDescription(description);
                boolean profileUpdated = profileDAO.update(profile);
                if (profileUpdated) {
                    this.outputResponse(resp, "Профиль обновлен в БД.", 200);
                } else {
                    this.outputResponse(resp, "Профиль не обновлен, так как в БД его нет", 404);
                }
            } else {
                this.outputResponse(resp, "Запрос сформулирован неверно.", 500);
            }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws  IOException{

        String pathInfo = req.getPathInfo();
        if (pathInfo.matches("\\/[0-9]+\\/{0,1}")) {
            String numberString = pathInfo.replace("/","");
            int number = Integer.parseInt(numberString);
            ProfileDAO profileDAO = new ProfileDAO();
            boolean isDeleted = profileDAO.delete(number);
            if (isDeleted) {
                this.outputResponse(resp, "Профиль удален", 200);
            } else {
                this.outputResponse(resp, "Профиль не удален", 404);
            }
        } else {
            this.outputResponse(resp, "Запрос сформулирован неверно", 500);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException{

        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {

                ProfileDAO profileDAO = new ProfileDAO();
                boolean isCreated = profileDAO.create(new Profile());
                if (isCreated) {
                    this.outputResponse(resp, "Профиль внесен в БД.", 200);
                } else {
                    this.outputResponse(resp, "Профиль не внесен в БД", 405);
                }
            } else {
                this.outputResponse(resp, "Запрос сформулирован неверно", 500);
            }
    }


    private void outputResponse(HttpServletResponse response, String payload, int status) {
        response.setHeader("Content-Type", "application/json");
        try {
            response.setStatus(status);
            if (payload != null) {
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(payload.getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String toJson(Object list){
        if (list == null) return null;
        Gson gson = new Gson();
        String json = null;
        try {
            json = gson.toJson(list);
        }
        catch (Exception e) {}
        return json;
    }
}
