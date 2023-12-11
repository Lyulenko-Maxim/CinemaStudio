package com.example.backend.dao;

import com.example.backend.entities.Photo;
import com.example.backend.entities.Profile;
import com.example.backend.entities.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PhotoDAO extends BaseDAO<Photo, Integer> {

    @Override
    public boolean create(Photo photo) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(photo);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public List<Photo> list() throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Photo").list();
        }
    }

    public List list(Integer id) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Profile profile = session.get(Profile.class,id);
            return profile.getPhotos();
        }
    }

    @Override
    public Photo retreive(Integer id) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Photo photo = session.get(Photo.class, id);
            transaction.commit();
            return photo;
        } catch (HibernateException e) {
            return null;
        }
    }

    @Override
    public boolean update(Photo entity) throws HibernateException {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Photo photo = session.get(Photo.class, id);
            session.remove(photo);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

}
