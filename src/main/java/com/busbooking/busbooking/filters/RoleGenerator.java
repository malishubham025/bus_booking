package com.busbooking.busbooking.filters;

import com.busbooking.busbooking.securityconfig.JWTValidator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class RoleGenerator extends OncePerRequestFilter {


    @Autowired
    JWTValidator validator;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        validator.validateToken(request);
        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if(request.getServletPath().matches("/login") || request.getServletPath().matches("/login") ){
            return true;
        }
        return false;
    }

}
