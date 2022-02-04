package com.midas.springjpa.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JWTUtil {

    private final Environment env;

    public String createToken(String email) {
        Algorithm algorithm = Algorithm.HMAC256(env.getProperty("jwt.secret"));
        return JWT.create()
                    .withIssuer(env.getProperty("jwt.issure"))
                    .withSubject(env.getProperty("jwt.subject"))
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + Long.valueOf(env.getProperty("jwt.expires_sec"))))
                    .withClaim(JWTClaims.EMAIL, email)
                    .sign(algorithm);
    }

    public String createRefreshToken(String email) {
        Algorithm algorithm = Algorithm.HMAC256(env.getProperty("jwt.refresh_secret"));
        return JWT.create()
                .withIssuer(env.getProperty("jwt.issure"))
                .withSubject(env.getProperty("jwt.subject"))
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + Long.valueOf(env.getProperty("jwt.expires_sec"))))
                .withClaim(JWTClaims.EMAIL, email)
                .sign(algorithm);
    }

    class JWTClaims {
        final static String EMAIL = "email";
    }
}
