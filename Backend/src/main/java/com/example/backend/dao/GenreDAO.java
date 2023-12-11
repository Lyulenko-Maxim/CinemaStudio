package com.example.backend.dao;

import com.example.backend.entities.Genres;
import com.example.backend.entities.Profile;
import com.example.backend.entities.Project;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class GenreDAO extends BaseDAO<Genres,Integer>{
    @Override
    public boolean create(Genres genres) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(genres);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }




    @Override
    public List<Genres> list() throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Genres", Genres.class).list();
        }
    }


    public Set<Genres> list(Integer id) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
          /*String st=  "SELECT t.name FROM Profile r JOIN r.projects t WHERE r.id =" +id;
          return session.createQuery(st).list();*/
            ProfileDAO profileDAO = new ProfileDAO();
            Profile pr = profileDAO.retreive(id);
            return pr.getGenres();
        }
    }


    @Override
    public Genres retreive(Integer id) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Genres genres = session.get(Genres.class, id);
            transaction.commit();
            return genres;
        } catch (HibernateException e) {
            return null;
        }
    }

    @Override
    public boolean update(Genres entity) throws HibernateException {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Genres genres = session.get(Genres.class,id);
            session.remove(genres);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }
}
