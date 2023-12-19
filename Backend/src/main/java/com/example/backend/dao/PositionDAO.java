package com.example.backend.dao;

import com.example.backend.entities.Position;
import com.example.backend.entities.Profile;
import com.example.backend.types.OrderType;
import jakarta.persistence.criteria.*;
import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.internal.FilterImpl;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;

public class PositionDAO extends GenericFilterDAO<Position, Long> {
    public PositionDAO() {
        super(Position.class);
    }
}
