package com.example.SpringSecurityJPA.controller;

import com.example.SpringSecurityJPA.model.User;
import com.example.SpringSecurityJPA.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/test")
    public String test(){
        return "Test API works";
    }

    //while getting password encode and store

    @PostMapping("/registerUser")
    public User registerUser(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return myUserDetailsService.registerUser(user);
    }

    @GetMapping("/admin")
    public String getAdmin(){
        return "Admin Called";
    }

    @GetMapping("/user")
    public String getUser(){
        return "User called";
    }


}
