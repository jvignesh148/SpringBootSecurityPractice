package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExampleController {

    @Autowired
    private CustomUserDetailService service;

    @PreAuthorize("permitAll")
    @GetMapping("/welcome")
    public String welcomeMessage(){
        return "Welcome to spring boot security application";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminAccess(){
        return "This is ADMIN'S access...\nif you are not admin please leave the page.\nIf you are ADMIN please ignore the above message";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/adduser")
    public void addUser(@RequestBody User user){
        service.addUser(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public List<User> fetchAllUsers(){
        return service.getAllUsers();
    }
}