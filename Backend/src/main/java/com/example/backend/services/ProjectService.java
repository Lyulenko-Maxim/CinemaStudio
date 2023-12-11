package com.example.backend.services;

import com.example.backend.dao.PhotoDAO;
import com.example.backend.dao.ProjectDAO;
import com.example.backend.entities.Photo;
import com.example.backend.entities.Project;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProjectService {
    private final ProjectDAO projectDAO;

    public ProjectService(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    public void findProject(int id) {
        projectDAO.retreive(id);
    }

    public void addProject(Project project) {
        projectDAO.create(project);
    }


    public void deleteProject(HttpServletRequest req, HttpServletResponse resp) {

    }

    public boolean createProject(String jsonPayload) {

        if (jsonPayload == null) return false;

        Gson gson = new Gson();

        try {
            Project project = (Project) gson.fromJson(jsonPayload, Project.class);
        }
        catch (Exception e) {}
        return false;
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
    public List<Project> findAllProjects() {
        return projectDAO.list();
    }
}
