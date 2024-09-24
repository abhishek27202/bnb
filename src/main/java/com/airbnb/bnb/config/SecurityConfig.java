package com.airbnb.bnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;

@Configuration
public class SecurityConfig {
    private JWTFilter jwtFilter ;
    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChian(
            HttpSecurity http

    ) throws Exception {
        http.csrf().disable().cors().disable();
       http.authorizeHttpRequests().anyRequest().permitAll();
//       http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
//        http.authorizeHttpRequests()
//                .requestMatchers("/api/v1/auth/**")
//                .permitAll()
//                .requestMatchers("/api/v1/auth/createpropertymanager").hasRole("ADMIN")
//                .anyRequest().authenticated();
        return http.build();

    }
}
