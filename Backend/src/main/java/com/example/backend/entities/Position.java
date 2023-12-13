package com.example.backend.entities;

import com.example.backend.shared.Salary;
import com.example.backend.types.ExperienceType;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "positions")
@FilterDefs({

        @FilterDef(name = "experienceFilter", parameters = {
                @ParamDef(name = "experienceParam", type = String.class)
        }),

        @FilterDef(name = "locationCityFilter", parameters = {
                @ParamDef(name = "locationCityParam", type = String.class)
        }),

        @FilterDef(name = "salaryMinAmountFilter", parameters = {
                @ParamDef(name = "salaryMinAmountParam", type = BigDecimal.class),
        }),

        @FilterDef(name = "salaryMaxAmountFilter", parameters = {
                @ParamDef(name = "salaryMaxAmountParam", type = BigDecimal.class)
        }),

        @FilterDef(name = "withSalaryFilter", parameters = {
                @ParamDef(name = "withSalaryParam", type = Boolean.class)
        }),

        @FilterDef(name = "scheduleFilter", parameters = {
                @ParamDef(name = "scheduleParam", type = String.class)
        }),


})
@Filters({

        @Filter(name = "experienceFilter",
                condition = "experience = :experienceParam"
        ),

        @Filter(name = "locationCityFilter",
                condition = "location_id IN (SELECT locations.id FROM locations WHERE locations.city = :locationCityParam)"
        ),

        @Filter(name = "salaryMinAmountFilter",
                condition = "salary_min_amount >= :salaryMinAmountParam AND salary_min_amount IS NOT NULL"
        ),

        @Filter(name = "salaryMaxAmountFilter",
                condition = "salary_max_amount <= :salaryMaxAmountParam AND salary_max_amount IS NOT NULL"
        ),

        @Filter(name = "withSalaryFilter",
                condition = "(:withSalaryParam = true AND (salary_type = 'FIXED' OR salary_type = 'HOURLY') OR :withSalaryParam = false)"
        ),

        @Filter(name = "scheduleFilter",
                condition = "id IN (SELECT ps.positions_id FROM positions_schedules ps JOIN schedules s ON ps.schedules_id = s.id WHERE s.type IN (:scheduleParam))"
        )

})
public class Position extends BaseEntity {
    @Expose
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vacancy_id", nullable = false)
    private Vacancy vacancy;

    @Expose
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profession_id", nullable = false)
    private Profession profession;

    @Expose
    @Enumerated(EnumType.STRING)
    @Column(name = "experience")
    private ExperienceType experience;

    @Expose
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Schedule> schedules = new HashSet<>();

    @Expose
    @Column(name = "responsibilities")
    private String responsibilities;

    @Expose
    @Column(name = "requirements")
    private String requirements;

    @Expose
    @ManyToOne(fetch = FetchType.EAGER)
    private Location location;

    @Expose
    @Column(name = "published")
    private Date published = new Date(System.currentTimeMillis());

    @Getter
    @Setter
    @Expose
    @Embedded
    private Salary salary;

}
