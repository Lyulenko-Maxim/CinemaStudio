package com.example.backend.filters;

import com.example.backend.services.JWTService;
import org.jose4j.jwt.consumer.InvalidJwtException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

//@WebFilter(urlPatterns = {"/secure/*"})
public class JWTFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String subject = httpRequest.getUserPrincipal().getName();

        String accessToken = JWTService.getAccessTokenFromCookies(httpRequest);
        String refreshToken = JWTService.getRefreshTokenFromCookies(httpRequest);
        if (accessToken == null || refreshToken == null) {
            JWTService.handleInvalidToken(httpResponse);
            return;
        }

        if (JWTService.verifyAccessToken(accessToken, subject)) {
            chain.doFilter(request, response);
            return;
        }

        if (!JWTService.verifyRefreshToken(refreshToken, subject)) {
            JWTService.handleInvalidToken(httpResponse);
            return;
        }

        String newAccessToken = JWTService.generateAccessToken(subject);
        String newRefreshToken = JWTService.generateRefreshToken(subject);

        if (newAccessToken == null || newRefreshToken == null) {
            JWTService.handleInvalidToken(httpResponse);
            return;
        }

        chain.doFilter(request, response);

        JWTService.setNewTokensInCookies((HttpServletResponse) response, newAccessToken, newRefreshToken);
    }
}