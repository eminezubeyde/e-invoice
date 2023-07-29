package com.example.einvoice.core.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.einvoice.core.security.helper.JwtHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@Component
public class CustomAuthorizationFilter  extends OncePerRequestFilter{
    private final JwtHelper jwtHelper;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/api/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        String authorizationHeader = request.getHeader("Auhtorization");


        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            try {
                String token = authorizationHeader.substring("Bearer ".length());
                String identity = jwtHelper.findUsername(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(identity);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(),null,userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                filterChain.doFilter(request,response);
            }catch (Exception e){
                Map<String, Object> customResponse = new HashMap<>();
                customResponse.put("isSuccessfull", false);
                customResponse.put("message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                response.setStatus(403);
                new ObjectMapper().writeValue(response.getOutputStream(), customResponse);
            }

        }else{
            filterChain.doFilter(request, response);
        }
    }


}
