package com.example.einvoice.core.security;

import com.example.einvoice.core.constant.security.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorizationFilter authorizationFilter;
    private final JWTHelper jwtHelper;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeHttpRequests().antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll();
        http.authorizeRequests().antMatchers("/api/**").hasAnyAuthority(Roles.ADMIN);
        http.authorizeHttpRequests().antMatchers("/api/filter/**").hasAnyAuthority(Roles.DRIVER);
        http.authorizeHttpRequests().anyRequest().authenticated();


        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean(), jwtHelper);
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");

        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
