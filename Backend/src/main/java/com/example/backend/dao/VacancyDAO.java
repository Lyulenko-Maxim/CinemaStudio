package com.example.backend.dao;

import com.example.backend.entities.Vacancy;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VacancyDAO extends BaseDAO<Vacancy, Integer> {


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
            return session.createQuery(criteria).getResultList();

        } catch (HibernateException e) {
            return null;
        }
    }

    public List<Vacancy> list(Map<String, String[]> filters) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Vacancy> criteria = builder.createQuery(Vacancy.class);
            Root<Vacancy> root = criteria.from(Vacancy.class);

            List<Predicate> predicates = new ArrayList<>();

            if (filters != null && !filters.isEmpty()) {
                for (Map.Entry<String, String[]> entry : filters.entrySet()) {
                    String field = entry.getKey();
                    Object value = entry.getValue();

                    if (value != null) {
                        predicates.add(builder.equal(root.get(field), value));
                    }
                }
            }

            if (!predicates.isEmpty()) {
                criteria.where(predicates.toArray(new Predicate[0]));
            }

            return session.createQuery(criteria).getResultList();

        } catch (HibernateException e) {
            return null;
        }
    }

//    public List<Predicate> buildPredicates(
//            CriteriaBuilder builder,
//            Root<Vacancy> root,
//            Map<String, String[]> filters
//    ) {
//        List<Predicate> predicates = new ArrayList<>();
//
//        if (filters != null && !filters.isEmpty()) {
//            for (Map.Entry<String, String[]> entry : filters.entrySet()) {
//                String field = entry.getKey();
//                Object value = entry.getValue();
//
//                if (value != null) {
//                    predicates.add(builder.equal(root.get(field), value));
//                }
//            }
//        }
//
//        return predicates;
//    }

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
