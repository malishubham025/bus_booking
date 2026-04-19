package com.busbooking.busbooking.securityconfig;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JWTValidator {

    public void validateToken(HttpServletRequest request) throws UserPrincipalNotFoundException{
        String token=request.getHeader("Authorization");
        if(token==null){
            throw new BadCredentialsException("Token does not exist");
        }
        SecretKey key= Keys.hmacShaKeyFor("longstringistherefortokengenertionandramdomval".getBytes());
        Claims claims=Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)  // ✅ not parseClaimsJwt
                .getBody();
        String username=claims.get("username").toString();
        String role=claims.get("roles").toString();
        String []roles=role.split(" ");
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for(String role2: roles){
            list.add(new SimpleGrantedAuthority(role2));
        }
        Authentication authentication=new UsernamePasswordAuthenticationToken(username,null,list);
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }
}
