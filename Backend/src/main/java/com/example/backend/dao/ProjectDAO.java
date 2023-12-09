package com.example.backend.dao;

import com.example.backend.entities.Photo;
import com.example.backend.entities.Project;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProjectDAO extends BaseDAO<Project,Integer>{
    @Override
    public boolean create(Project project) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(project);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public List<Project> list() throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Project", Project.class).list();
        }
    }


    @Override
    public Project retreive(Integer id) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Project project = session.get(Project.class, id);
            transaction.commit();
            return project;
        } catch (HibernateException e) {
            return null;
        }
    }

    @Override
    public boolean update(Project entity) throws HibernateException {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Project project = session.get(Project.class, id);
            session.remove(project);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }
}
