package com.example.backend.dao;

import com.example.backend.entities.Vacancy;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.*;

import java.util.List;

public class VacancyDAO extends BaseDAO<Vacancy, Integer> {
    public VacancyDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public boolean create(Vacancy vacancy) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(vacancy);
            transaction.commit();
            return true;

        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public List<Vacancy> list() throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Vacancy> criteria = builder.createQuery(Vacancy.class);
            criteria.from(Vacancy.class);
            return session.createQuery(criteria).getResultList();

        } catch (HibernateException e) {
            return null;
        }
    }

    @Override
    public Vacancy retreive(Integer id) throws HibernateException {
        return null;
    }

    @Override
    public boolean update(Vacancy entity) throws HibernateException {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws HibernateException {
        return false;
    }
}
