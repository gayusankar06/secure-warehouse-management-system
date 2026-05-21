package com.warehouse.controller;

import com.warehouse.entity.User;
import com.warehouse.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    // REGISTER API
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {

        return userService.registerUser(user);

    }

    // LOGIN API
    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {

        return userService.loginUser(
                user.getEmail(),
                user.getPassword()
        );

    }

}