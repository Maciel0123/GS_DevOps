package com.clima.gs.gs.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.clima.gs.gs.model.Token;
import com.clima.gs.gs.model.User;

@Service
public class TokenService {

    private Instant experiesAt = LocalDateTime.now().plusMinutes(120).toInstant(ZoneOffset.ofHours(-3));
    private Algorithm algorithm = Algorithm.HMAC256("secret-muito-secreto-que-ninguem-pode-saber");

    public String generateJwt(User user) {
        return JWT.create()
                .withSubject(user.getIdUser().toString())
                .withClaim("email", user.getEmail())
                .withExpiresAt(experiesAt)
                .sign(algorithm);
    }

    public User getUserFromToken(String jwt) {
        var jwtVerified = JWT.require(algorithm).build().verify(jwt);
        return User.builder()
                .idUser(Long.valueOf(jwtVerified.getSubject()))
                .email(jwtVerified.getClaim("email").asString())
                .build();

    }

}