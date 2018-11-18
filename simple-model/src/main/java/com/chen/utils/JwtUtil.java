package com.chen.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chen.model.person.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    public static String create(Person person, String secret, String issuer) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withClaim("name", person.getName())
                .withClaim("id", person.getId())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withIssuer(issuer)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1L * 30 * 24 * 3600 * 1000))
                .sign(algorithm);
        return token;
    }

    public static DecodedJWT verify(String token, String secret, String issuer) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build(); //Reusable verifier instance
            return verifier.verify(token);
        } catch (JWTVerificationException exception) {
            logger.error("[jwt verify error]", exception);
            return null;
        } catch (Exception e) {
            logger.error("[jwt verify error]", e);
            return null;
        }
    }

}
