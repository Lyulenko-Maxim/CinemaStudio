//package com.example.backend.dao;
//
//import com.example.backend.entities.User;
//import jakarta.persistence.criteria.Predicate;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import java.util.List;
//
//public class UserDAO extends BaseDAO<User, Integer> {
//
//    @Override
//    public boolean create(User user) throws HibernateException {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.persist(user);
//            transaction.commit();
//            return true;
//
//        } catch (HibernateException e) {
//            return false;
//        }
//    }
//
//    @Override
//    public List<User> list() throws HibernateException {
//        return null;
//    }
//
//    @Override
//    public User retreive(Integer id) throws HibernateException {
//        return null;
//    }
//
//    @Override
//    public boolean update(User entity) throws HibernateException {
//        return false;
//    }
//
//    @Override
//    public boolean delete(Integer id) throws HibernateException {
//        return false;
//    }
//}
