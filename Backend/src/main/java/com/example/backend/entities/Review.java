package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review  extends BaseEntity{

    @Column(name = "review", nullable = false)
    private String review;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender", nullable = false)
    private Profile sender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver", nullable = false)
    private Profile receiver;

}
