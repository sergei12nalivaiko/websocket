package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {

   private UserService userService;

   @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("registration/{username}")
    public ResponseEntity<Void> register(@PathVariable String username){
        System.out.println("handling register user request: " + username);
        userService.saveUser(username);
        return ResponseEntity.ok().build();
    }

    public List<User> fetchAll(){
       return userService.fetchAllUsers();
    }



}
