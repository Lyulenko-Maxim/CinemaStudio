package com.example.backend.servlets;

import com.example.backend.dao.ProfileDAO;
import com.example.backend.entities.Profile;
import com.example.backend.entities.Project;
import com.example.backend.utils.HibernateUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.hibernate.SessionFactory;
import org.jose4j.lang.StringUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.stream.Collectors;

@WebServlet("/profiles/*")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

            String pathInfo = req.getPathInfo();
        if (pathInfo.matches("\\/[0-9]+\\/{0,1}")) {
            String numberString = pathInfo.replace("/", "");
            int number = Integer.parseInt(numberString);
            ProfileDAO profileDAO = new ProfileDAO();
            Gson gson = new GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation()
                        .create();
            String json = gson.toJson(profileDAO.retreive(number));
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        String pathInfo = req.getPathInfo();
        String reqBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        int rc = HttpServletResponse.SC_OK;
        Gson gson = new Gson();

        try {
            if (pathInfo.matches("\\/[0-9]+\\/{0,1}")) {
                ProfileDAO profileDAO = new ProfileDAO();
                String numberString = pathInfo.replace("/", "");
                int number = Integer.parseInt(numberString);
                Profile profile = (Profile) gson.fromJson(reqBody, Profile.class);
                Profile profile_old = profileDAO.retreive(number);


                profile_old.setSurname(profile.getSurname());
                profile_old.setName(profile.getName());
                profile_old.setBirthplace(profile.getBirthplace());

                profile_old.setEmail(profile.getEmail());
                profile_old.setExperience(profile.getExperience());
             
                profile_old.setEducation(profile.getEducation());
                profile_old.setInstitution(profile.getInstitution());


                boolean profileUpdated = profileDAO.update(profile_old);
                if (profileUpdated) {
                    this.outputResponse(resp, "Профиль обновлен в БД.", 200);
                } else {
                    this.outputResponse(resp, "Профиль не обновлен, так как в БД его нет", 404);
                }
            } else {
                this.outputResponse(resp, "Запрос сформулирован неверно.", 500);
            }
        }
        catch (Exception e){}
        this.outputResponse(resp, null,rc);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws  IOException{

        String pathInfo = req.getPathInfo();
        if (pathInfo.matches("\\/[0-9]+\\/{0,1}")) {
            if (true) {
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
