//package com.example.backend.dao;
//
//import com.example.backend.entities.Genre;
//import com.example.backend.entities.Profile;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import java.util.List;
//import java.util.Set;
//
//public class GenreDAO extends BaseDAO<Genre,Integer>{
//    @Override
//    public boolean create(Genre genres) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.persist(genres);
//            transaction.commit();
//            return true;
//        } catch (HibernateException e) {
//            return false;
//        }
//    }
//
//
//
//
//    @Override
//    public List<Genre> list() throws HibernateException {
//        try (Session session = sessionFactory.openSession()) {
//            return session.createQuery("from Genre ", Genre.class).list();
//        }
//    }
//
//
//    public Set<Genre> list(Integer id) throws HibernateException {
//        try (Session session = sessionFactory.openSession()) {
//          /*String st=  "SELECT t.name FROM Profile r JOIN r.projects t WHERE r.id =" +id;
//          return session.createQuery(st).list();*/
//            ProfileDAO profileDAO = new ProfileDAO();
//            Profile pr = profileDAO.retreive(id);
//            return pr.getGenres();
//        }
//    }
//
//
//    @Override
//    public Genre retreive(Integer id) throws HibernateException {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            Genre genres = session.get(Genre.class, id);
//            transaction.commit();
//            return genres;
//        } catch (HibernateException e) {
//            return null;
//        }
//    }
//
//    @Override
//    public boolean update(Genre entity) throws HibernateException {
//        return false;
//    }
//
//    @Override
//    public boolean delete(Integer id) throws HibernateException {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            Genre genres = session.get(Genre.class,id);
//            session.remove(genres);
//            transaction.commit();
//            return true;
//        } catch (HibernateException e) {
//            return false;
//        }
//    }
//}
