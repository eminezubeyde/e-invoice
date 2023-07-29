package com.example.einvoice.core.security.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.einvoice.core.exception.GeneralException;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Component
public class JwtHelper {
    public String create(String username, List<String> roles) throws GeneralException {
        if(username.isBlank()){
            throw new GeneralException("username cannot be null");
        }
        return JWT.create()
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+30000000L))
                .withSubject(username)
                .withClaim("roles",roles)
                .sign(Algorithm.HMAC512("secretKey"));

    }
    public String findUsername(String token){
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
