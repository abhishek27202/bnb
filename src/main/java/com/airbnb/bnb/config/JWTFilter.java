package com.airbnb.bnb.config;

import com.airbnb.bnb.Entity.AppUser;
import com.airbnb.bnb.Repository.AppUserRepository;
import com.airbnb.bnb.Service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private JWTService jwtService;
    private AppUserRepository appUserRepository;

    public JWTFilter(JWTService jwtService, AppUserRepository appUserRepository) {
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token=request.getHeader("Authorization");
        if(token!=null&& token.startsWith("Bearer ")){
            String tokenVal=token.substring(8,token.length()-1);
           String username= jwtService. getUserName(tokenVal);
        Optional<AppUser> opUser= appUserRepository.findByUsername(username);
        if(opUser.isPresent()){
          AppUser appUser=  opUser.get();

            UsernamePasswordAuthenticationToken
                    auth=new UsernamePasswordAuthenticationToken( appUser,null, Collections.singletonList (new SimpleGrantedAuthority(appUser.getRole())));
            auth.setDetails(new WebAuthenticationDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        }
        filterChain.doFilter(request, response);

    }
}
