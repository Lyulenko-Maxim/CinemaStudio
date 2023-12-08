package com.example.backend.dao;

import com.example.backend.entities.Vacancy;
import com.example.backend.utils.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public abstract class BaseDAO<E, K> {
    protected static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public abstract boolean create(E entity) throws HibernateException;

    public abstract List<E> list() throws HibernateException;

    public abstract E retreive(K id) throws HibernateException;

    public abstract boolean update(E entity) throws HibernateException;

    public abstract boolean delete(K id) throws HibernateException;
}
