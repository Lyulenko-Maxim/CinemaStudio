package com.example.backend.services;

import com.example.backend.dao.VacancyDAO;
import com.example.backend.entities.Vacancy;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class VacancyService {
    private final VacancyDAO vacancyDAO = new VacancyDAO();

    public List<Vacancy> searchVacancies(HttpServletRequest request) {
        return vacancyDAO.list(request.getParameterMap());
    }


}
