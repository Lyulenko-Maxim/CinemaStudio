package com.example.backend.dao;

import com.example.backend.entities.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO extends BaseDAO<User, Integer> {
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public boolean create(User user) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return true;

        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public ArrayList<User> list() throws HibernateException {
        return null;
    }

    @Override
    public User retreive(Integer id) throws HibernateException {
        return null;
    }

    @Override
    public boolean update(User entity) throws HibernateException {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws HibernateException {
        return false;
    }
}
