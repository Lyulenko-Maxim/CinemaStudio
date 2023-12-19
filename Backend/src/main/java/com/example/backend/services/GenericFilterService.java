package com.example.backend.services;

import com.example.backend.types.OrderType;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

public abstract class GenericFilterService {
    protected final HttpServletRequest request;

    protected GenericFilterService(HttpServletRequest request) {
        this.request = request;
    }

    protected abstract Map<String, OrderType> createOrdersMap();

    protected abstract Map<String, Map<String, Object>> createFiltersMap();

    protected void addOrderIfNotBlank(Map<String, OrderType> filtersMap, String paramName) {
        String orderType = request.getParameter(paramName);
        if (orderType == null || orderType.isBlank()) return;
        filtersMap.put(paramName, (orderType.equalsIgnoreCase(String.valueOf(OrderType.ASC))
                ? OrderType.ASC
                : OrderType.DESC
        ));
    }

    protected void addFilterIfNotBlank(Map<String, Map<String, Object>> filtersMap, String paramName) {
        String paramValue = request.getParameter(paramName);
        if (paramValue == null || paramValue.isBlank()) return;
        filtersMap.put(
                paramName + "Filter",
                Collections.singletonMap(paramName + "Param", paramValue)
        );
    }

    protected void addFilterIfNotBlankArray(Map<String, Map<String, Object>> filtersMap, String paramName) {
        String[] paramValues = request.getParameterValues(paramName);
        if (paramValues == null || paramValues.length == 0) return;
        filtersMap.put(
                paramName + "Filter",
                Collections.singletonMap(paramName + "Param", paramValues)
        );
    }
}
