package com.example.backend.dao;

import com.example.backend.entities.Photo;
import com.example.backend.entities.Profile;
import com.example.backend.entities.Project;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Set;

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

    public boolean create(Integer prof_id, Long proj_id) {
        try (Session session = sessionFactory.openSession()) {
            String q =  "INSERT INTO profile_projects(profile_id, project_id) VALUES(" + prof_id + "," + proj_id + ")";
            session.createNativeQuery(q);
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


    public Set<Project> list(Integer id) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
          /*String st=  "SELECT t.name FROM Profile r JOIN r.projects t WHERE r.id =" +id;
          return session.createQuery(st).list();*/
            ProfileDAO profileDAO = new ProfileDAO();
            Profile pr = profileDAO.retreive(id);
            return pr.getProjects();
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

            Project project = session.get(Project.class,id);
            session.remove(project);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

}
