package com.example.backend.services;

import com.example.backend.dao.ProfileDAO;
import com.example.backend.entities.Profile;
import com.example.backend.types.OrderType;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileService extends GenericFilterService {
    private final ProfileDAO profileDAO = new ProfileDAO();

    public ProfileService(HttpServletRequest request) {
        super(request);
    }

    public List<Profile> getProfiles() {
        return profileDAO.list();
    }

    public List<Profile> getCandidatesProfiles() {
        Map<String, Map<String, Object>> filtersMap = createFiltersMap();
        filtersMap.put(
                "roleFilter",
                Collections.singletonMap("roleParam", "CANDIDATE")
        );
        Map<String, OrderType> ordersMap = createOrdersMap();
        return profileDAO.list(filtersMap, ordersMap);
    }

    @Override
    protected Map<String, Map<String, Object>> createFiltersMap() {
        Map<String, Map<String, Object>> filtersMap = new HashMap<>();
        addFilterIfNotBlank(filtersMap, "isBusy");
        addFilterIfNotBlank(filtersMap, "experience");
        addFilterIfNotBlank(filtersMap, "withSalary");
        addFilterIfNotBlank(filtersMap, "locationCity");
        addFilterIfNotBlank(filtersMap, "minAge");
        addFilterIfNotBlank(filtersMap, "maxAge");
        addFilterIfNotBlank(filtersMap, "Age");
        addFilterIfNotBlank(filtersMap, "gender");
        addFilterIfNotBlank(filtersMap, "withPhotoOnly");
        return filtersMap;
    }

    @Override
    protected Map<String, OrderType> createOrdersMap() {
        Map<String, OrderType> ordersMap = new HashMap<>();
        addOrderIfNotBlank(ordersMap, "rating");
        addOrderIfNotBlank(ordersMap, "minAmount");
        return ordersMap;
    }


}
