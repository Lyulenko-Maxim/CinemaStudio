package com.example.backend.servlets;

import com.example.backend.entities.Position;
import com.example.backend.services.PositionService;
import com.example.backend.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/*")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PositionService positionService = new PositionService(req);
        List<Position> positions = positionService.getPositions();
        ServletUtils.writeJsonResponse(resp, positions);
    }
}
