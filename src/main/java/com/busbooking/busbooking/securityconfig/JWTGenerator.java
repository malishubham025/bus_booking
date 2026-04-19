package com.busbooking.busbooking.securityconfig;

import com.busbooking.busbooking.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class JWTGenerator {

    public void generateToken(User user, HttpServletResponse response){
        String username= user.getUsername();
        String auth=user.getRole();
        SecretKey key= Keys.hmacShaKeyFor("longstringistherefortokengenertionandramdomval".getBytes());
        String token=Jwts.builder()
                .setIssuer("Bus Booking Backend")
                .claim("username",username)
                .claim("roles",auth)
                .signWith(key)
                .compact();
        response.setHeader("Authorization",token);
        String []roles=auth.split(" ");
        List<SimpleGrantedAuthority>authorities=new ArrayList<>();
        for(String role:roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        Authentication authentication=new UsernamePasswordAuthenticationToken(username,null,authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
