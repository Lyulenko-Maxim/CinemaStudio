package com.example.backend.entities;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "profiles")
public class Profile  extends BaseEntity{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Expose
    @Setter
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "profile_profession",
            joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "profession_id", referencedColumnName = "id")
    )
    private Set<Profession> professions;

    @Expose
    @Setter
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "profile_genres",
            joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id")
    )
    private Set<Genres> genres;

    @Expose
    @Setter
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "profile_projects",
            joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id")
    )
    private Set<Project> projects;

    @Expose
    @OneToMany(targetEntity = Photo.class,mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Photo> photos;


    @Expose
    @Column(name="birth_date")
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
