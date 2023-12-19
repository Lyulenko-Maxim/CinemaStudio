package com.example.backend.dao;

import com.example.backend.types.OrderType;
import jakarta.persistence.criteria.*;
import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public interface FilterDAO<E, K> {
    List<E> list(Map<String, Map<String, Object>> filtersMap, Map<String, OrderType> orderMap) throws HibernateException;

    private void applyOrders(CriteriaQuery<E> criteria, CriteriaBuilder builder, Root<E> root, Map<String, OrderType> orderMap) {

    }

    private void enableFilters(Session session, Map<String, Map<String, Object>> filtersMap) {

    }

    private void disableFilters(Session session, Map<String, Map<String, Object>> filtersMap) {

    }

    private void setFilterParameter(Filter filter, String paramName, Object paramValue) throws NullPointerException, IllegalArgumentException {

    }

    private Type getFilterParameterType(Filter filter, String paramName) {
        return null;
    }

    private Path<Object> getOrderPath(Root<E> root, String orderField) {
        return null;
    }

    private Object convertToType(Type targetType, Object value) throws NullPointerException, IllegalArgumentException {
        return null;
    }
}
