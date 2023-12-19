package com.example.backend.dao;

import com.example.backend.utils.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class GenericDAO<E, K> implements BaseDAO<E, K> {
    @Getter
    protected static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    protected final Class<E> entityClass;

    public GenericDAO(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public boolean create(E entity) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public List<E> list() throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<E> criteria = builder.createQuery(entityClass);
            Root<E> root = criteria.from(entityClass);
            criteria.select(root);
            return session.createQuery(criteria).getResultList();
        } catch (HibernateException e) {
            return null;
        }
    }

    @Override
    public E retreive(K id) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(entityClass, id);
        } catch (HibernateException e) {
            return null;
        }
    }

    @Override
    public boolean update(E entity) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public boolean delete(K id) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            E entity = session.get(entityClass, id);
            session.remove(entity);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }
}
