package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    boolean isExist(String username);
    void saveUser(String username);
    List<User> fetchAllUsers();
}
