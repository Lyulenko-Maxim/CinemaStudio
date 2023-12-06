package com.example.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "professions")
public class Profession extends BaseEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;

}
