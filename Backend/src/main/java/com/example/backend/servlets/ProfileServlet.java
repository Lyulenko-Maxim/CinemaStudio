package com.example.backend.servlets;

import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/profile/*")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            MysqlLessonDAO lessonDAO = new MysqlLessonDAO();
            String json = this.toJson(lessonDAO.findAll());
            this.outputResponse(resp, json, 200);
        } else {
            if (pathInfo.matches("\\/[0-9]+\\/{0,1}")) {
                String numberString = pathInfo.replace("/", "");
                int number = Integer.parseInt(numberString);
                MysqlLessonDAO lessonDAO = new MysqlLessonDAO();
                String json = this.toJson(lessonDAO.readBySystemId(number));
                if (json == null) {
                    this.outputResponse(resp, "Занятие не найдено.", 404);
                }
                else {
                    this.outputResponse(resp,json,200);
                }
            } else {
                this.outputResponse(resp, "Запрос сформулирован неверно.", 500);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws  IOException{
        String pathInfo = req.getPathInfo();

        if (pathInfo.matches("\\/[0-9]+\\/{0,1}")) {

            if (req.getParameter("id_subject") != null && req.getParameter("id_teacher") != null && req.getParameter("day") != null && req.getParameter("classroom") !=null && req.getParameter("students_count") !=null && req.getParameter("quantity") !=null) {
                String numberString = pathInfo.replace("/", "");
                int number = Integer.parseInt(numberString);
                int idSubject = Integer.parseInt(req.getParameter("id_subject"));
                int idTeacher = Integer.parseInt(req.getParameter("id_teacher"));
                int day = Integer.parseInt(req.getParameter("day"));
                int classroom = Integer.parseInt(req.getParameter("classroom"));
                int students_count = Integer.parseInt(req.getParameter("students_count"));
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                MysqlLessonDAO lessonDAO = new MysqlLessonDAO();
                Lesson les = new Lesson(idSubject, idTeacher, day, classroom, students_count, quantity);
                les.setId(number);
                boolean lessonUpdated = lessonDAO.update(les);
                if (lessonUpdated) {
                    this.outputResponse(resp, "Занятие обновлено в БД.", 200);
                } else {
                    this.outputResponse(resp, "Занятие не обновлено, так как в БД его нет", 404);
                }
            } else {
                this.outputResponse(resp, "Запрос сформулирован неверно. Нужно: path/{id}?id_subject=?&id_teacher=?&day=?&classroom=?&students_count=?&quantity=?", 500);
            }
        } else {
            this.outputResponse(resp, "Запрос сформулирован неверно", 500);
        }
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws  IOException{

        String pathInfo = req.getPathInfo();
        if (pathInfo.matches("\\/[0-9]+\\/{0,1}")) {
            String numberString = pathInfo.replace("/","");
            int number = Integer.parseInt(numberString);
            MysqlLessonDAO lessonDAO = new MysqlLessonDAO();
            boolean isDeleted = lessonDAO.delete(number);
            if (isDeleted) {
                this.outputResponse(resp, "Занятие удалено", 200);
            } else {
                this.outputResponse(resp, "Занятие не удалено. В БД не было занятия с таким номером", 404);
            }
        } else {
            this.outputResponse(resp, "Запрос сформулирован неверно", 500);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException{

        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            if(req.getParameter("id_subject") != null && req.getParameter("id_teacher") != null && req.getParameter("day") != null && req.getParameter("classroom") !=null && req.getParameter("students_count") !=null && req.getParameter("quantity") !=null){
                MysqlLessonDAO lessonDAO = new MysqlLessonDAO();
                int idSubject = Integer.parseInt(req.getParameter("id_subject"));
                int idTeacher = Integer.parseInt(req.getParameter("id_teacher"));
                int day = Integer.parseInt(req.getParameter("day"));
                int classroom = Integer.parseInt(req.getParameter("classroom"));
                int students_count = Integer.parseInt(req.getParameter("students_count"));
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                boolean isCreated = lessonDAO.create(new Lesson(idSubject,idTeacher,day,classroom,students_count,quantity));
                if (isCreated) {
                    this.outputResponse(resp, "Занятие внесено в БД.", 200);
                } else {
                    this.outputResponse(resp, "Занятие не внесено в БД", 405);
                }
            } else {
                this.outputResponse(resp, "Запрос сформулирован неверно", 500);
            }
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
