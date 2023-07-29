package com.example.einvoice.core.security.filter;

import com.example.einvoice.core.exception.GeneralException;
import com.example.einvoice.core.result.DataResult;
import com.example.einvoice.core.result.LoginResponse;
import com.example.einvoice.core.security.helper.JwtHelper;
import com.example.einvoice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class CustomAuthanticationFilter extends UsernamePasswordAuthenticationFilter  {
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;
    private final UserService userService;

    @Override
    public Authentication attemptAuthentication(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("identity");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(token);    }

    @Override
    protected void successfulAuthentication(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain chain, Authentication authentication) throws IOException, jakarta.servlet.ServletException {
        DataResult<LoginResponse>  result=new DataResult<>();
        // kayıt olurken rolleri verilir.

        try {
            String token = jwtHelper.create(authentication.getName(), userService.getUserRoles(authentication.getName()));
            response.setContentType(MediaType.APPLICATION_JSON_VALUE); // ->
            response.setStatus(200);
            result.setIsSuccessful(true);
            result.setMessage("Giriş Başarılı");
            result.setData(new LoginResponse(token));
            new ObjectMapper().writeValue(response.getOutputStream(),result);

        } catch (GeneralException e) {
            throw new IOException(e);
        }
    }






}
