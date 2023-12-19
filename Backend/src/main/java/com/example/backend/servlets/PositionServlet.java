package com.example.backend.servlets;

import com.example.backend.entities.Position;
import com.example.backend.services.PositionService;
import com.example.backend.utils.ServletUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Lombok;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet(urlPatterns = {"/positions/*"})
public class PositionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getRequestURI()
                .substring(req.getContextPath().length() + "/positions/".length());

        if (pathInfo.isBlank()) {
            PositionService positionService = new PositionService(req);
            List<Position> positions = positionService.getPositions();
            ServletUtils.writeJsonResponse(resp, positions);
            return;
        }

        long id;
        try {
            id = Long.parseLong(pathInfo);

        } catch (NumberFormatException ignored) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid path");
            return;
        }

        PositionService positionService = new PositionService(req);
        Position position = positionService.getPosition(id);

        if (position == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("Position not found");
            return;
        }

        ServletUtils.writeJsonResponse(resp, position);
    }
}
