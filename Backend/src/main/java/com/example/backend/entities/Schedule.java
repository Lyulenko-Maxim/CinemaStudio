package com.example.backend.entities;

import com.example.backend.types.ScheduleType;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "schedules")
public class Schedule extends BaseEntity {
    @Expose
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private ScheduleType type;

    @ManyToMany(mappedBy = "schedules")
    private Set<Position> positions = new HashSet<>();
}
