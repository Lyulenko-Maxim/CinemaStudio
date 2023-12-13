package com.example.backend.entities;

import com.google.gson.annotations.Expose;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "professions")
public class Profession extends BaseEntity {
    @Expose
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "professions")
    private Set<Profile> profiles = new HashSet<>();
}
