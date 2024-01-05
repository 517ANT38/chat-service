package com.service.chatservice.configuration.security.auth;

import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String login,String role) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());

        return JWT.create()
                .withSubject("User details")
                .withClaim("login", login)
                .withClaim("role",role)
                .withIssuedAt(new Date())
                .withIssuer("astService")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("astService")
                .build();

        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("login").asString();
    }
}