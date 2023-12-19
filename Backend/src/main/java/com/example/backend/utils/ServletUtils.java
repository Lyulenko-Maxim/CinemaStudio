package com.example.backend.utils;

import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class ServletUtils {
    public static String toJson(Object object) {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
                .toJson(object);
    }

    public static void writeJsonResponse(HttpServletResponse resp, Object data) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (OutputStream out = resp.getOutputStream()) {
            out.write(toJson(data).getBytes());
            out.flush();
        }
    }
}
