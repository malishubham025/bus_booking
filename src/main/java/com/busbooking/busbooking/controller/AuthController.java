package com.busbooking.busbooking.controller;

import com.busbooking.busbooking.entity.User;
import com.busbooking.busbooking.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;
    @PostMapping("/login")
    ResponseEntity login(@RequestBody User user,HttpServletResponse response){
        return authService.handleLogin(user,response);
    }

    @PostMapping("/signup")
    ResponseEntity signup(@RequestBody User user,HttpServletResponse response){
        return authService.handleSignup(user,  response);
    }

    @PostMapping("/logout")
    ResponseEntity logout(){
        return new ResponseEntity(HttpStatusCode.valueOf(200));
    }
}
