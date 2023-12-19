//package com.example.backend.services;
//
//import com.example.backend.dao.ProjectDAO;
//import com.example.backend.dao.ReviewDAO;
//import com.example.backend.entities.Project;
//import com.example.backend.entities.Review;
//
//public class ReviewService {
//
//    private final ReviewDAO reviewDAO;
//
//    public ReviewService(ReviewDAO reviewDAO) {
//        this.reviewDAO = reviewDAO;
//    }
//
//    public void findReview(int id) {
//        reviewDAO.retreive(id);
//    }
//
//    public void addReview(Review review) {
//        reviewDAO.create(review);
//    }
//
//    public void deleteReview(int id) {
//        reviewDAO.delete(id);
//    }
//    public void findAllReviews() {
//        reviewDAO.list();
//    }
//}
