package com.example.backend.servlets;

import com.example.backend.dao.ProfileDAO;
import com.example.backend.dao.ProjectDAO;
import com.example.backend.entities.Profession;
import com.example.backend.entities.Profile;
import com.example.backend.entities.Project;
import com.example.backend.services.ProjectService;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@WebServlet("/projects/*")
public class ProjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            String pathInfo = req.getPathInfo();
        if (pathInfo.matches("\\/[0-9]+\\/{0,1}")) {
            String numberString = pathInfo.replace("/", "");
            int number = Integer.parseInt(numberString);
            ProjectDAO projectDAO = new ProjectDAO();
            String json = this.toJson(projectDAO.list(number));
            if (json == null) {
                this.outputResponse(resp, "Проекты не найдены.", 404);
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

            boolean profileUpdated=false;
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
            int id = Integer.parseInt(numberString);
            String name = req.getParameter("name");
            ProjectDAO projectDAO = new ProjectDAO();
            ProfileDAO profileDAO = new ProfileDAO();
            Profile profile = profileDAO.retreive(id);
            for (Project project : profile.getProjects()) {
                if (project.getName().equals(name)) {
                    profile.getProjects().remove(project);
                    profileDAO.update(profile);
                    this.outputResponse(resp, "Проект удален", 200);
                    return;
                }
                else {
                    this.outputResponse(resp, "Проект не удален", 404);
                    return;
                }
            }
        } else {
            this.outputResponse(resp, "Запрос сформулирован неверно", 500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException{
        String pathInfo = req.getPathInfo();
        if (pathInfo.matches("\\/[0-9]+\\/{0,1}")) {
            String numberString = pathInfo.replace("/","");
            int id = Integer.parseInt(numberString);
            String name = req.getParameter("name");
            Project project = new Project();
            project.setName(name);
            ProjectDAO projectDAO = new ProjectDAO();
            ProfileDAO profileDAO = new ProfileDAO();
            List<Project> allProjects = projectDAO.list();
            Profile profile = profileDAO.retreive(id);
            Set<Project> pr = profile.getProjects();
            for (int i=0; i < allProjects.size(); i++) {
                if (Objects.equals(allProjects.get(i).getName(), name)) {
                    try {
                        profile.getProjects().add(allProjects.get(i));
                        profileDAO.update(profile);
                        this.outputResponse(resp, "Проект внесен в profile_projects. ПУПУ", 200);
                        return;
                    }
                    catch(Exception e) {
                        this.outputResponse(resp, "Проект не внесен в БД", 405);
                        return;
                    }
                }
            }
            boolean isCreated = projectDAO.create(project);
            profile.getProjects().add(project);
            profileDAO.update(profile);
            this.outputResponse(resp, "Проект внесен в profile_projects. ПУПУ2", 200);
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
