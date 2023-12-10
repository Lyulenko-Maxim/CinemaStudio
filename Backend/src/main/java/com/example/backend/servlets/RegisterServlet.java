package com.example.backend.servlets;

import com.example.backend.dao.UserDAO;
import com.example.backend.entities.User;
import com.example.backend.services.UserService;
import com.example.backend.utils.HibernateUtil;
import com.example.backend.exceptions.InvalidPhoneNumberException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Register", urlPatterns = {"/register/"})
public class RegisterServlet extends HttpServlet {
    private final UserService userService;

    public RegisterServlet() {
        UserDAO userDao = new UserDAO();
        userService = new UserService(userDao);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");

        // Создание нового пользователя
        User user = new User();
        try {
            user.setPhoneNumber(phoneNumber);
            user.setPassword(password);


            userService.register(user);

            // Отправка успешного ответа
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write("User registered successfully");
        } catch (InvalidPhoneNumberException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid phone number");
        }
    }
}

