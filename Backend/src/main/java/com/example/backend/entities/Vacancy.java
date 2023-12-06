package com.example.backend.entities;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vacancies")
@Getter
@Setter
public class Vacancy extends BaseEntity {

    @Expose
    @Column(name = "name", nullable = false)
    private String name;

    @Expose
    @Column(name = "description")
    private String description;

    @Expose
    @OneToMany(mappedBy = "vacancy", fetch = FetchType.EAGER)
    private Set<Position> positions;

    @Expose
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employer_id", nullable = false)
    private Employer employer;

    @Expose
    @Lob
    @Column(name = "cover")
    private byte[] imageData;

}
