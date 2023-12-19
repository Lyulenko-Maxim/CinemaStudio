//package com.example.backend.entities;
//
//
//import com.google.gson.annotations.Expose;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "photos")
//public class Photo extends BaseEntity {
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "profile_id", nullable = false)
//    private Profile profile;
//
//    @Expose
//    @Column(name = "image", nullable = false)
//    private String image;
//
//    public void setImage(String image) {
//        this.image = "http://10.0.3.2:8080/api/images/" + image;
//    }
//}
