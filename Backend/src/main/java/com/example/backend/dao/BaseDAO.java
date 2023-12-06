package com.example.backend.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO<E, K> {
    protected final SessionFactory sessionFactory;

    protected BaseDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public abstract boolean create(E entity) throws HibernateException;

    public abstract List<E> list() throws HibernateException;

    public abstract E retreive(K id) throws HibernateException;

    public abstract boolean update(E entity) throws HibernateException;

    public abstract boolean delete(K id) throws HibernateException;

}
