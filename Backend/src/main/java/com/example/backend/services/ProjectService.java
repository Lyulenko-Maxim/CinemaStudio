package com.example.backend.services;

import com.example.backend.dao.PhotoDAO;
import com.example.backend.dao.ProjectDAO;
import com.example.backend.entities.Photo;
import com.example.backend.entities.Project;

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

    public void deleteProject(int id) {
        projectDAO.delete(id);
    }

    public void findAllProjects() {
        projectDAO.list();
    }
}
