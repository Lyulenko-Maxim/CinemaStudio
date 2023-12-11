package com.example.backend.dao;

import com.example.backend.entities.Profile;
import com.example.backend.entities.Project;
import com.example.backend.entities.Review;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReviewDAO extends BaseDAO<Review, Integer>{
    @Override
    public boolean create(Review review) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(review);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public List<Review> list() throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Review ", Review.class).list();
        }
    }

    public List<Review> list(Integer id) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            ProfileDAO profileDAO = new ProfileDAO();
            Profile profile = profileDAO.retreive(id);
            return profile.getReceiverReviews();
        }
    }


    @Override
    public Review retreive(Integer id) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Review review = session.get(Review.class, id);
            transaction.commit();
            return review;
        } catch (HibernateException e) {
            return null;
        }
    }

    @Override
    public boolean update(Review entity) throws HibernateException {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Review review = session.get(Review.class, id);
            session.remove(review);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }
}
