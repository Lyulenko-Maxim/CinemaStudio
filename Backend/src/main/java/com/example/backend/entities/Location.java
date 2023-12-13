package com.example.backend.entities;

import com.google.gson.annotations.Expose;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "locations")
public class Location extends BaseEntity {
    @Expose
    @Column(name = "country")
    private String country;

    @Expose
    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "location")
    private Set<Position> positions = new HashSet<>();

}
