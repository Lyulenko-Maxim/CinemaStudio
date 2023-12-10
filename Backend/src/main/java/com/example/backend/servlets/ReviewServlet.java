package com.example.backend.servlets;

import com.example.backend.dao.PhotoDAO;
import com.example.backend.dao.ReviewDAO;
import com.example.backend.entities.Photo;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/reviews/")
public class ReviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String pathInfo = req.getServletPath();
        if (pathInfo.matches("\\/[0-9]+\\/{0,1}")) {
            String numberString = pathInfo.replace("/", "");
            int number = Integer.parseInt(numberString);
            ReviewDAO reviewDAO = new ReviewDAO();
            String json = this.toJson(reviewDAO.retreive(number));
            if (json == null) {
                this.outputResponse(resp, "Отзыв не найдены.", 404);
            }
            else {
                this.outputResponse(resp,json,200);
            }
        } else {
            this.outputResponse(resp, "Запрос сформулирован неверно.", 500);
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws  IOException{
        String pathInfo = req.getPathInfo();

        if (pathInfo.matches("\\/[0-9]+\\/{0,1}")) {

            boolean photoUpdated = false;
            if (photoUpdated) {
                this.outputResponse(resp, "Профиль обновлен в БД.", 200);
            } else {
                this.outputResponse(resp, "Профиль не обновлен, так как в БД его нет", 404);
            }
        } else {
            this.outputResponse(resp, "Запрос сформулирован неверно.", 500);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws  IOException{

        String pathInfo = req.getPathInfo();
        if (pathInfo.matches("\\/[0-9]+\\/{0,1}")) {
            String numberString = pathInfo.replace("/","");
            int number = Integer.parseInt(numberString);
            PhotoDAO photoDAO = new PhotoDAO();
            boolean isDeleted = photoDAO.delete(number);
            if (isDeleted) {
                this.outputResponse(resp, "Фото удалено", 200);
            } else {
                this.outputResponse(resp, "Фото не удалено", 404);
            }
        } else {
            this.outputResponse(resp, "Запрос сформулирован неверно", 500);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException{

        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            PhotoDAO photoDAO = new PhotoDAO();
            boolean isCreated = photoDAO.create(new Photo());
            if (isCreated) {
                this.outputResponse(resp, "Фото внесено в БД.", 200);
            } else {
                this.outputResponse(resp, "Фото не внесено в БД", 405);
            }
        } else {
            this.outputResponse(resp, "Запрос сформулирован неверно", 500);
        }
    }


    private void outputResponse(HttpServletResponse response, String payload, int status) {
        response.setHeader("Content-Type", "application/json");
        try {
            response.setStatus(status);
            if (payload != null) {
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(payload.getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String toJson(Object list){
        if (list == null) return null;
        Gson gson = new Gson();
        String json = null;
        try {
            json = gson.toJson(list);
        }
        catch (Exception e) {}
        return json;
    }
}
