package com.example.backend.entities;

import com.google.gson.annotations.Expose;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "currencies")
public class Currency extends BaseEntity {
    @Expose
    @Column(name = "name")
    private String name;
}
