package com.example.backend.entities;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "profiles")
public class Profile extends BaseEntity {

    @Expose
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Expose
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Profession> professions = new HashSet<>();

    @Expose
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Genres> genres = new HashSet<>();

    @Expose
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Project> projects = new HashSet<>();

    @Expose
    @Column(name = "birth_date")
    @OneToMany(targetEntity = Photo.class, mappedBy = "profile", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Photo> photos;


    @OneToMany(targetEntity = Review.class, mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Review> SenderReviews;

    @Expose
    @OneToMany(targetEntity = Review.class, mappedBy = "receiver", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Review> ReceiverReviews;

    @Expose
    @Column(name="name")
    private String name;

    @Expose
    @Column(name="surname")
    private String surname;

    @Expose
    @Column(name="email")
    private String email;


    @Expose
    @Column(name = "birth_date")
    private Date birthdate;

    @Expose
    @Lob
    @Column(name = "avatar")
    private byte[] avatar;

    @Expose
    @Column(name = "birth_place")
    private String birthplace;

    @Expose
    @Column(name = "experience")
    private String experience;

    @Expose
    @Column(name = "education")
    private String education;

    @Expose
    @Column(name = "institution")
    private String institution;

    @Expose
    @Column(name = "description")
    private String description;

}
