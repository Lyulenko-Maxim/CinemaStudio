package com.example.backend.entities;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project extends BaseEntity {
    @Expose
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "projects")
    private Set<Profile> profiles = new HashSet<>();
}
