package com.example.backend.dao;

import com.example.backend.entities.Position;
import com.example.backend.types.OrderType;
import jakarta.persistence.criteria.*;
import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.internal.FilterImpl;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;

public class PositionDAO extends BaseDAO<Position, Integer> {
    @Override
    public List<Position> list() throws HibernateException {
        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Position> criteria = builder.createQuery(Position.class);
            Root<Position> root = criteria.from(Position.class);
            criteria.select(root);
            return session.createQuery(criteria).getResultList();
        } catch (HibernateException e) {
            return null;
        }
    }

    public List<Position> list(Map<String, Map<String, Object>> filtersMap,
                               Map<String, OrderType> orderMap) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Position> criteria = builder.createQuery(Position.class);
            Root<Position> root = criteria.from(Position.class);
            criteria.select(root);

            applyOrders(criteria, builder, root, orderMap);
            enableFilters(session, filtersMap);
            List<Position> positions = session.createQuery(criteria).getResultList();
            disableFilters(session, filtersMap);
            return positions;

        } catch (HibernateException e) {
            return null;
        }
    }

    private void applyOrders(CriteriaQuery<Position> criteria, CriteriaBuilder builder, Root<Position> root,
                             Map<String, OrderType> orderMap) {

        if (orderMap == null || orderMap.isEmpty()) return;
        List<Order> orders = new ArrayList<>();
        for (Map.Entry<String, OrderType> orderEntry : orderMap.entrySet()) {
            String orderField = orderEntry.getKey();
            OrderType orderType = orderEntry.getValue();
            if (orderField == null || orderType == null) continue;

            Path<Object> orderPath = getOrderPath(root, orderField);
            if (orderPath == null) continue;

            orders.add(orderType == OrderType.ASC
                    ? builder.asc(orderPath)
                    : builder.desc(orderPath)
            );
        }
        criteria.orderBy(orders);
    }

    private Path<Object> getOrderPath(Root<Position> root, String orderField) {
        try {
            return root.get(orderField);
        } catch (IllegalArgumentException | IllegalStateException ignored) {
        }

        try {
            return root.get("salary").get(orderField);
        } catch (IllegalArgumentException | IllegalStateException ignored) {
            return null;
        }
    }

    private static void enableFilters(Session session, Map<String, Map<String, Object>> filtersMap) {
        for (Map.Entry<String, Map<String, Object>> entry : filtersMap.entrySet()) {
            Filter filter = session.enableFilter(entry.getKey());
            Map<String, Object> filterParams = entry.getValue();

            for (Map.Entry<String, Object> paramEntry : filterParams.entrySet()) {
                try {
                    setFilterParameter(filter, paramEntry.getKey(), paramEntry.getValue());
                } catch (IllegalArgumentException | NullPointerException e) {
                    session.disableFilter(entry.getKey());
                    break;
                }
            }
        }
    }

    private static void setFilterParameter(Filter filter, String paramName,
                                           Object paramValue) throws NullPointerException, IllegalArgumentException {
        Type expectedParam = getFilterParameterType(filter, paramName);
        System.out.println(expectedParam);
        if (paramValue != null && paramValue.getClass().isArray()) {
            Object[] convertedArray = Arrays.stream((Object[]) paramValue)
                    .map(param -> convertToType(expectedParam, param))
                    .toArray();

            filter.setParameterList(paramName, convertedArray);
            return;
        }
        System.out.println(convertToType(expectedParam, paramValue).getClass());
        filter.setParameter(paramName, convertToType(expectedParam, paramValue));
    }

    private static Type getFilterParameterType(Filter filter, String paramName) {
        return ((FilterImpl) filter)
                .getFilterDefinition()
                .getParameterJdbcMapping(paramName)
                .getJavaTypeDescriptor()
                .getJavaType();
    }

    private static Object convertToType(Type targetType,
                                        Object value) throws NullPointerException, IllegalArgumentException {
        if (targetType.equals(String.class)) return value.toString();
        if (targetType.equals(Integer.class)) return Integer.valueOf(value.toString());
        if (targetType.equals(BigDecimal.class)) return new BigDecimal(value.toString());
        if (targetType.equals(Boolean.class)) return Boolean.parseBoolean(value.toString());

        throw new IllegalArgumentException();
    }

    private static void disableFilters(Session session, Map<String, Map<String, Object>> filtersMap) {
        for (Map.Entry<String, Map<String, Object>> entry : filtersMap.entrySet()) {
            session.disableFilter(entry.getKey());
        }
    }

    @Override
    public boolean create(Position entity) throws HibernateException {
        return false;
    }

    @Override
    public Position retreive(Integer id) throws HibernateException {
        return null;
    }

    @Override
    public boolean update(Position entity) throws HibernateException {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws HibernateException {
        return false;
    }

}
