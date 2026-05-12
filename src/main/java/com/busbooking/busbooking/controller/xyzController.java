package com.busbooking.busbooking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class xyzController {
    @GetMapping("/xyz")
    public String hello(){
        return "hello";
    }
    @PostMapping("/xyz")
    public String hell2o(@RequestBody int a){
        return "hello";
    }

    @PostMapping("/pqr")
    public String hell2o(){
        return "hello";
    }
}
