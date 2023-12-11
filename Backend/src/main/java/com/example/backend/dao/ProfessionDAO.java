package com.example.backend.dao;

import com.example.backend.entities.Genres;
import com.example.backend.entities.Profession;
import com.example.backend.entities.Profile;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class ProfessionDAO extends BaseDAO<Profession,Integer>{
    @Override
    public boolean create(Profession profession) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(profession);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }
    @Override
    public List<Profession> list() throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Profession ", Profession.class).list();
        }
    }


    public Set<Profession> list(Integer id) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
          /*String st=  "SELECT t.name FROM Profile r JOIN r.projects t WHERE r.id =" +id;
          return session.createQuery(st).list();*/
            ProfileDAO profileDAO = new ProfileDAO();
            Profile pr = profileDAO.retreive(id);
            return pr.getProfessions();
        }
    }


    @Override
    public Profession retreive(Integer id) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Profession profession = session.get(Profession.class, id);
            transaction.commit();
            return profession;
        } catch (HibernateException e) {
            return null;
        }
    }

    @Override
    public boolean update(Profession entity) throws HibernateException {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Profession profession = session.get(Profession.class,id);
            session.remove(profession);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }
}
