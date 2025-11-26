package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.List;

public interface UserServiceInterface {
    void addUser(User user);

    List<User> getAllUsers();
}
