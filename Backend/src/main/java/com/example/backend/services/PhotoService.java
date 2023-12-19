//package com.example.backend.services;
//
//import com.example.backend.dao.PhotoDAO;
//import com.example.backend.entities.Photo;
//
//public class PhotoService {
//    private final PhotoDAO photoDAO;
//
//    public PhotoService(PhotoDAO photoDAO) {
//        this.photoDAO = photoDAO;
//    }
//
//    public void findPhoto(int id) {
//        photoDAO.retreive(id);
//    }
//
//    public void addPhoto(Photo photo) {
//        photoDAO.create(photo);
//    }
//
//    public void deletePhoto(int id) {
//        photoDAO.delete(id);
//    }
//
//    public void findAllPhotos() {
//        photoDAO.list();
//    }
//}
