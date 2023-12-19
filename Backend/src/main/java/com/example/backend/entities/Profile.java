package com.example.backend.entities;

import com.example.backend.shared.Salary;
import com.example.backend.types.ExperienceType;
import com.example.backend.types.GenderType;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FilterDefs({
        @FilterDef(name = "minAgeFilter", parameters = @ParamDef(name = "minAgeParam", type = Integer.class)),
        @FilterDef(name = "maxAgeFilter", parameters = @ParamDef(name = "maxAgeParam", type = Integer.class)),
        @FilterDef(name = "isBusyFilter", parameters = @ParamDef(name = "isBusyParam", type = Boolean.class)),
        @FilterDef(name = "withPhotoOnlyFilter", parameters = @ParamDef(name = "withPhotoOnlyParam", type = Boolean.class)),
        @FilterDef(name = "roleFilter", parameters = @ParamDef(name = "roleParam", type = String.class)),
        @FilterDef(name = "genderFilter", parameters = @ParamDef(name = "genderParam", type = String.class)),
})
@Filters({
        @Filter(name = "roleFilter",
                condition = "user_id IN (SELECT ur.users_id FROM users_roles ur JOIN roles r ON ur.roles_id = r.id WHERE r.name = :roleParam)"
        ),
        @Filter(name = "isBusyFilter",
                condition = "is_busy = :isBusyParam"
        ),
        @Filter(name = "experienceFilter",
                condition = "experience = :experienceParam"
        ),
        @Filter(name = "withSalaryFilter",
                condition = "(:withSalaryParam = true AND (salary_type = 'FIXED' OR salary_type = 'HOURLY') OR :withSalaryParam = false)"
        ),
        @Filter(name = "locationCityFilter",
                condition = "location_id IN (SELECT locations.id FROM locations WHERE locations.city = :locationCityParam)"
        ),
        @Filter(name = "genderFilter",
                condition = "gender = :genderParam"
        ),
        @Filter(name = "minAgeFilter",
                condition = "age >= :minAgeParam"
        ),
        @Filter(name = "maxAgeFilter",
                condition = "age <= :maxAgeParam"
        ),
        @Filter(name = "withPhotoOnlyFilter",
                condition = "(:withPhotoOnlyParam = true AND avatar IS NOT NULL) OR (:withPhotoOnlyParam = false)"
        ),
})
@Entity
@Table(name = "profiles")
public class Profile extends BaseEntity {

    @Expose
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Expose
    @Column(name = "name")
    private String name;

    @Expose
    @Column(name = "surname")
    private String surname;

    @Expose
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderType gender;

    @Expose
    @Column(name = "age")
    private Integer age;

    @Expose
    @Column(name = "is_busy")
    private boolean isBusy;

    @Expose
    @Column(name = "rating")
    private float rating;

    @Expose
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Profession> professions = new HashSet<>();

    @Expose
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Genre> genres = new HashSet<>();

    @Expose
    @Column(name = "avatar")
    private String avatar;

    @Expose
    @ManyToOne(fetch = FetchType.EAGER)
    private Location location;

    @Expose
    @Enumerated(EnumType.STRING)
    @Column(name = "experience")
    private ExperienceType experience;

    @Expose
    @Column(name = "education")
    private String education;

    @Expose
    @Column(name = "institution")
    private String institution;

    @Expose
    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Expose
    @SerializedName("expectedSalary")
    @Embedded
    private Salary expectedSalary;

    public void setAvatar(String avatar) {
        this.avatar = "http://10.0.3.2:8080/api/images/" + avatar;
    }

    public Profile(
            User user,
            String name,
            String surname,
            GenderType gender,
            int age,
            Set<Profession> professions,
            Set<Genre> genres,
            String avatar,
            Location location,
            ExperienceType experience,
            String education,
            String institution,
            String description,
            boolean isBusy,
            float rating,
            Salary expectedSalary
    ) {
        this.user = user;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.age = age;
        this.isBusy = isBusy;
        this.rating = rating;
        this.professions = professions;
        this.genres = genres;
        this.avatar = avatar;
        this.location = location;
        this.experience = experience;
        this.education = education;
        this.institution = institution;
        this.description = description;
        this.expectedSalary = expectedSalary;
    }

    public Profile(
            User user,
            String name,
            String surname,
            GenderType gender,
            int age,
            Set<Profession> professions,
            Set<Genre> genres,
            String avatar,
            Location location,
            String education,
            String institution,
            String description
    ) {
        this.user = user;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.age = age;
        this.professions = professions;
        this.genres = genres;
        this.avatar = avatar;
        this.location = location;
        this.education = education;
        this.institution = institution;
        this.description = description;
    }
}
