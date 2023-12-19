//package com.example.backend.servlets;
//
//import com.example.backend.dao.PhotoDAO;
//import com.example.backend.dao.ProfileDAO;
//import com.example.backend.dao.ProjectDAO;
//import com.example.backend.dao.ReviewDAO;
//import com.example.backend.entities.Photo;
//import com.example.backend.entities.Profile;
//import com.example.backend.entities.Project;
//import com.example.backend.entities.Review;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//@WebServlet("/reviews/*")
//public class ReviewServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//
//        String pathInfo = req.getPathInfo();
//        if (pathInfo.matches("\\/[0-9]+\\/{0,1}")) {
//            String numberString = pathInfo.replace("/", "");
//            int number = Integer.parseInt(numberString);
//            ReviewDAO reviewDAO = new ReviewDAO();
//            Gson gson = new GsonBuilder()
//                    .excludeFieldsWithoutExposeAnnotation()
//                    .create();
//            String json = gson.toJson(reviewDAO.list(number));
//            if (json == null) {
//                this.outputResponse(resp, "Отзывы не найдены.", 404);
//            }
//            else {
//                this.outputResponse(resp,json,200);
//        }
//    }   else {
//            this.outputResponse(resp, "Запрос сформулирован неверно.", 500);
//        }
//    }
//
//
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws  IOException{
//        String pathInfo = req.getPathInfo();
//
//        if (pathInfo.matches("\\/[0-9]+\\/{0,1}")) {
//
//            boolean photoUpdated = false;
//            if (photoUpdated) {
//                this.outputResponse(resp, "Профиль обновлен в БД.", 200);
//            } else {
//                this.outputResponse(resp, "Профиль не обновлен, так как в БД его нет", 404);
//            }
//        } else {
//            this.outputResponse(resp, "Запрос сформулирован неверно.", 500);
//        }
//    }
//
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws  IOException{
//
//        String pathInfo = req.getPathInfo();
//        if (pathInfo.matches("\\/[0-9]+\\/{0,1}")) {
//            String numberString = pathInfo.replace("/","");
//            int number = Integer.parseInt(numberString);
//            PhotoDAO photoDAO = new PhotoDAO();
//            boolean isDeleted = false;
//            if (isDeleted) {
//                this.outputResponse(resp, "Фото удалено", 200);
//            } else {
//                this.outputResponse(resp, "Фото не удалено", 404);
//            }
//        } else {
//            this.outputResponse(resp, "Запрос сформулирован неверно", 500);
//        }
//    }
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException{
//
//        String reqBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//        System.out.println(reqBody);
//
//        int rc = HttpServletResponse.SC_OK;
//        Gson gson = new Gson();
//        try {
//            Review review = (Review) gson.fromJson(reqBody, Review.class);
//            String pathInfo = req.getPathInfo();
//            if (pathInfo.matches("\\/[0-9]+\\/{0,1}")) {
//                String numberString = pathInfo.replace("/", "");
//                int id = Integer.parseInt(numberString);
//                int receiver_id = Integer.parseInt(req.getParameter("id"));
//                try {
//                    ProfileDAO profileDAO = new ProfileDAO();
//                    ReviewDAO reviewDAO = new ReviewDAO();
//                    Profile profileReceiver = profileDAO.retreive(receiver_id);
//                    Profile profileSender = profileDAO.retreive(id);
//                    review.setReceiver(profileReceiver);
//                    review.setSender(profileSender);
//                    reviewDAO.create(review);
//
//                    this.outputResponse(resp, "Отзыв внесен", 200);
//                }
//                catch (Exception e) {
//                    this.outputResponse(resp, "Отзыв не внесен", 405);
//                    return;
//                }
//            }
//        }
//        catch (Exception e) {}
//        this.outputResponse(resp, null,rc);
//    }
//
//
//    private void outputResponse(HttpServletResponse response, String payload, int status) {
//        response.setHeader("Content-Type", "application/json");
//        try {
//            response.setStatus(status);
//            if (payload != null) {
//                OutputStream outputStream = response.getOutputStream();
//                outputStream.write(payload.getBytes());
//                outputStream.flush();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String toJson(Object list){
//        if (list == null) return null;
//        Gson gson = new Gson();
//        String json = null;
//        try {
//            json = gson.toJson(list);
//        }
//        catch (Exception e) {}
//        return json;
//    }
//}
