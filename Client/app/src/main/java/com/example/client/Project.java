package com.example.client;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;


    public Project(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
    private static List<Project> projects = new ArrayList<>();
    public static List<Project> getPro() {
        projects.clear();
        projects.add(new Project("Ёлки 1"));
        projects.add(new Project("Ёлки 2"));
        projects.add(new Project("Ёлки 3"));
        return projects;
    }
}
