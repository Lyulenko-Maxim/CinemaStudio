package com.example.backend.dao;

import com.example.backend.entities.Profile;
import com.example.backend.entities.Project;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProfileDAO extends BaseDAO<Profile, Integer>{
    @Override
    public boolean create(Profile profile) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(profile);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public List<Profile> list() throws HibernateException {
       return null;
    }


    @Override
    public Profile retreive(Integer id) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Profile profile = session.get(Profile.class, id);
            transaction.commit();
            return profile;
        } catch (HibernateException e) {
            return null;
        }
    }

    @Override
    public boolean update(Profile profile) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(profile);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Profile profile = session.get(Profile.class, id);
            session.remove(profile);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }
}
