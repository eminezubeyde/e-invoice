package com.example.einvoice.core.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;


@Component
public class JWTHelper {

    public String generate(String identity, List<String> roles) {
        if (!StringUtils.hasLength(identity)) {
            throw new IllegalArgumentException("Identifier no cannot be null");
        }

        String tokenValue = JWT.create()
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3000000))
                .withSubject(identity)
                .withClaim("roles", roles)
                .sign(Algorithm.HMAC512("secretKey"));
        return tokenValue;
    }

    public String findIdentity(String token) {
        if (!StringUtils.hasLength(token)) {
            throw new IllegalArgumentException("Token can not be null or empty");
        }
        return JWT.decode(token).getSubject();
    }


    public void validate(String tokenValue) throws InvalidClaimException, SignatureVerificationException, AlgorithmMismatchException {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC512("secretKey")).build();
            jwtVerifier.verify(tokenValue);
        } catch (TokenExpiredException tokenExpiredException) {
            throw new TokenExpiredException("JWT Token expired");
        }
    }

}
