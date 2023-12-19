package com.example.backend.entities;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vacancies")
public class Vacancy extends BaseEntity {

    @Expose
    @Column(name = "name", nullable = false)
    private String name;

    @Expose
    @Column(name = "description", columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "vacancy", fetch = FetchType.EAGER)
    private Set<Position> positions = new HashSet<>();

    @Expose
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employer_profile_id", nullable = false)
    private Profile employerProfile;

    @Expose
    @Column(name = "cover")
    private String cover;
    public void setCover(String cover) {
        this.cover = "http://10.0.3.2:8080/api/images/" + cover;
    }

    public Vacancy(String name, String description, Profile employeeProfile, String cover){
        this.name = name;
        this.description = description;
        this.employerProfile = employeeProfile;
        this.cover = cover;
    }

}
