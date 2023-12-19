package com.example.backend.services;

import com.example.backend.dao.PositionDAO;
import com.example.backend.entities.Position;
import com.example.backend.types.OrderType;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionService extends GenericFilterService {
    private final PositionDAO positionDAO = new PositionDAO();

    public PositionService(HttpServletRequest request) {
        super(request);
    }

    public List<Position> getPositions() {
        Map<String, Map<String, Object>> filtersMap = createFiltersMap();
        Map<String, OrderType> ordersMap = createOrdersMap();
        return filtersMap.isEmpty() && ordersMap.isEmpty()
                ? positionDAO.list()
                : positionDAO.list(filtersMap, ordersMap);
    }

    public Position getPosition(long id) {
        return positionDAO.retreive(id);
    }

    @Override
    protected Map<String, OrderType> createOrdersMap() {
        Map<String, OrderType> ordersMap = new HashMap<>();

        addOrderIfNotBlank(ordersMap, "published");
        addOrderIfNotBlank(ordersMap, "minAmount");

        return ordersMap;
    }

    @Override
    protected Map<String, Map<String, Object>> createFiltersMap() {
        Map<String, Map<String, Object>> filtersMap = new HashMap<>();

        addFilterIfNotBlank(filtersMap, "experience");
        addFilterIfNotBlank(filtersMap, "locationCity");
        addFilterIfNotBlank(filtersMap, "salaryMinAmount");
        addFilterIfNotBlank(filtersMap, "salaryMaxAmount");
        addFilterIfNotBlank(filtersMap, "withSalary");

        addFilterIfNotBlankArray(filtersMap, "schedule");
        return filtersMap;
    }
}
