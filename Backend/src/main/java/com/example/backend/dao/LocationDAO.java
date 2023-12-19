package com.example.backend.dao;

import com.example.backend.entities.Location;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class LocationDAO extends GenericDAO<Location, Integer> {
    public LocationDAO() {
        super(Location.class);
    }
}
