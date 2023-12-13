package com.example.backend.services;

import com.example.backend.dao.PositionDAO;
import com.example.backend.dao.UserDAO;
import com.example.backend.entities.Position;
import com.example.backend.types.OrderType;
import jakarta.persistence.OrderBy;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionService {
    private final PositionDAO positionDAO = new PositionDAO();

    public List<Position> getPositions(HttpServletRequest request) {
        Map<String, Map<String, Object>> filtersMap = createFiltersMap(request);
        Map<String, OrderType> ordersMap = createOrdersMap(request);
        return filtersMap.isEmpty() && ordersMap.isEmpty()
                ? positionDAO.list()
                : positionDAO.list(filtersMap, ordersMap);
    }

    private Map<String, OrderType> createOrdersMap(HttpServletRequest request) {
        Map<String, OrderType> ordersMap = new HashMap<>();

        addOrderIfNotBlank(request, ordersMap, "published");
        addOrderIfNotBlank(request, ordersMap, "minAmount");
        addOrderIfNotBlank(request, ordersMap, "id");

        return ordersMap;
    }

    private Map<String, Map<String, Object>> createFiltersMap(HttpServletRequest request) {
        Map<String, Map<String, Object>> filtersMap = new HashMap<>();

        addFilterIfNotBlank(request, filtersMap, "experience");
        addFilterIfNotBlank(request, filtersMap, "locationCity");
        addFilterIfNotBlank(request, filtersMap, "salaryMinAmount");
        addFilterIfNotBlank(request, filtersMap, "salaryMaxAmount");
        addFilterIfNotBlank(request, filtersMap, "withSalary");

        addFilterIfNotBlankArray(request, filtersMap, "schedule");
        return filtersMap;
    }

    private void addOrderIfNotBlank(HttpServletRequest request,
                                    Map<String, OrderType> filtersMap,
                                    String paramName) {

        String orderType = request.getParameter(paramName);
        if (orderType == null || orderType.isBlank()) return;
        filtersMap.put(paramName, (orderType.equalsIgnoreCase(String.valueOf(OrderType.ASC))
                ? OrderType.ASC
                : OrderType.DESC
        ));
    }

    private void addFilterIfNotBlank(HttpServletRequest request,
                                     Map<String, Map<String, Object>> filtersMap,
                                     String paramName) {

        String paramValue = request.getParameter(paramName);
        if (paramValue == null || paramValue.isBlank()) return;
        filtersMap.put(
                paramName + "Filter",
                Collections.singletonMap(paramName + "Param", paramValue)
        );
    }

    private void addFilterIfNotBlankArray(HttpServletRequest request,
                                          Map<String, Map<String, Object>> filtersMap,
                                          String paramName) {

        String[] paramValues = request.getParameterValues(paramName);
        if (paramValues == null || paramValues.length == 0) return;
        filtersMap.put(
                paramName + "Filter",
                Collections.singletonMap(paramName + "Param", paramValues)
        );
    }
}
