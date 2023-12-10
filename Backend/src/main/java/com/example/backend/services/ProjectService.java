package com.example.backend.services;

import com.example.backend.dao.PhotoDAO;
import com.example.backend.dao.ProjectDAO;
import com.example.backend.entities.Photo;
import com.example.backend.entities.Project;

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
        String pathInfo = req.getPathInfo();
        if (pathInfo.matches("\\/[0-9]+\\/{0,1}")) {
            String str = pathInfo.replace("/", "");
            int[] nums = new int[str.length()];
            for (int i = 0; i < str.length(); i++) {
                nums[i] = Character.getNumericValue(str.charAt(i));
            }

        }
    }

    public List<Project> findAllProjects() {
        return projectDAO.list();
    }
}
