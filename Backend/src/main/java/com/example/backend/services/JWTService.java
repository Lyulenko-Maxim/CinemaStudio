package com.example.backend.services;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTService {
    private static final Config config = ConfigFactory.load();
    private static final String ACCESS_TOKEN_NAME = "access_token";
    private static final String REFRESH_TOKEN_NAME = "refresh_token";

    private static NumericDate getExpirationTime(int expirationSeconds) {
        return NumericDate.fromSeconds(NumericDate.now().getValue() + expirationSeconds);
    }

    private static String generateToken(int expirationSeconds, String subject) {
        JwtClaims claims = new JwtClaims();
        claims.setIssuer(config.getString("JWT.ISSUER"));
        claims.setSubject(subject);
        claims.setIssuedAtToNow();
        claims.setExpirationTime(getExpirationTime(expirationSeconds));

        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
        jws.setKey(new HmacKey(config.getString("JWT.SECRET_KEY").getBytes()));

        try {
            return jws.getCompactSerialization();
        } catch (JoseException e) {
            return null;
        }
    }

    private static boolean verifyToken(String token, String expectedSubject) {
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime()
                .setExpectedSubject(expectedSubject)
                .setExpectedIssuer(config.getString("JWT.ISSUER"))
                .setVerificationKey(new HmacKey(config.getString("JWT.SECRET_KEY").getBytes()))
                .build();

        try {
            jwtConsumer.processToClaims(token);
        } catch (InvalidJwtException e) {
            return false;
        }
        return true;
    }

    public static String generateAccessToken(String subject)  {
        return generateToken(config.getInt("JWT.ACCESS_EXP"), subject);
    }

    public static String generateRefreshToken(String subject) {
        return generateToken(config.getInt("JWT.REFRESH_EXP"), subject);
    }

    public static boolean verifyAccessToken(String accessToken, String expectedSubject) {
        return verifyToken(accessToken, expectedSubject);
    }

    public static boolean verifyRefreshToken(String refreshToken, String expectedSubject) {
        return verifyToken(refreshToken, expectedSubject);
    }

    public static Cookie getCookieByName(HttpServletRequest request, String cookieName) {
        if (request.getCookies() == null) {
            return null;
        }

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(cookieName)) {
                return cookie;
            }
        }

        return null;
    }

    public static String getAccessTokenFromCookies(HttpServletRequest request) {
        Cookie accessTokenCookie = getCookieByName(request, ACCESS_TOKEN_NAME);
        if (accessTokenCookie != null) {
            return accessTokenCookie.getValue();
        }
        return null;
    }

    public static String getRefreshTokenFromCookies(HttpServletRequest request) {
        Cookie refreshTokenCookie = getCookieByName(request, REFRESH_TOKEN_NAME);
        if (refreshTokenCookie != null) {
            return refreshTokenCookie.getValue();
        }
        return null;
    }

    public static void setNewTokensInCookies(HttpServletResponse response, String newAccessToken, String newRefreshToken) {
        Cookie accessTokenCookie = new Cookie("access_token", newAccessToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setMaxAge(config.getInt("JWT.REFRESH_EXP"));

        Cookie refreshTokenCookie = new Cookie("refresh_token", newRefreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setMaxAge(config.getInt("JWT.REFRESH_EXP"));

        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);
    }

    public static void handleInvalidToken(HttpServletResponse response) throws IOException {
        response.getWriter().write("Invalid or missing token");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}


