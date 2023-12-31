//package com.example.backend.entities;
//
//import com.google.gson.annotations.Expose;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.Set;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "reviews")
//public class Review  extends BaseEntity{
//
//    @Expose
//    @Column(name = "review", nullable = false)
//    private String review;
//
//    @Expose
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "sender", nullable = false)
//    private Profile sender;
//
//    @Expose
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "receiver", nullable = false)
//    private Profile receiver;
//}
