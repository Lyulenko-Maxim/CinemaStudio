package com.example.backend.services;

import com.example.backend.dao.VacancyDAO;

public class VacancyService {
    private final VacancyDAO vacancyDAO;

    public VacancyService(VacancyDAO vacancyDAO) {
        this.vacancyDAO = vacancyDAO;
    }
}
