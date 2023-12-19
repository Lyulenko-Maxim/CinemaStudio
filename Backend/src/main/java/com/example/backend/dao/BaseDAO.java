package com.example.backend.dao;

import com.example.backend.entities.Vacancy;
import com.example.backend.utils.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface BaseDAO<E, K> {
    boolean create(E entity) throws HibernateException;

    List<E> list() throws HibernateException;

    E retreive(K id) throws HibernateException;

    boolean update(E entity) throws HibernateException;

    boolean delete(K id) throws HibernateException;
}
