package com.busbooking.busbooking.service;

import com.busbooking.busbooking.entity.User;
import com.busbooking.busbooking.repository.UserRepository;
import com.busbooking.busbooking.securityconfig.CustomAuthenticationManager;
import com.busbooking.busbooking.securityconfig.CustomUserDetailsService;
import com.busbooking.busbooking.securityconfig.JWTGenerator;
import com.busbooking.busbooking.securityconfig.JWTValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class AuthService {

    @Autowired
    CustomAuthenticationManager manager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository repository;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    JWTGenerator generator;
    public ResponseEntity handleSignup(User user, HttpServletResponse response){
        UserDetails userDetails=userDetailsService.loadUserByUsername(user.getUsername());
        String password=user.getPassword();
        password=encoder.encode(password);
        user.setPassword(password);
        user.setRole("ROLE_USER");
        if(userDetails != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }
        repository.save(user);

        generator.generateToken(user,response);

        return new ResponseEntity<>("user logged in successfully",HttpStatusCode.valueOf(200));
    }

    public ResponseEntity handleLogin(User user,HttpServletResponse response){

        Authentication authentication= UsernamePasswordAuthenticationToken.unauthenticated(user.getUsername(),user.getPassword());
        authentication=manager.authenticate(authentication);
//        user.setRole("ROLE_USER");
        List<SimpleGrantedAuthority>authorities=(List<SimpleGrantedAuthority>)authentication.getAuthorities();
        String roles = authorities.stream()
                .map(auth -> auth.getAuthority())
                .reduce("", (a, b) -> a + " " + b)
                .trim();

        user.setRole(roles);
        generator.generateToken(user,response);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
