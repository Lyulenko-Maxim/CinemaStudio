package com.example.backend.entities;

import com.example.backend.shared.Salary;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "positions")
public class Position extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacancy_id", nullable = false)
    private Vacancy vacancy;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "responsibilities")
    private String responsibilities;

    @Embedded
    private Salary salary;

}
