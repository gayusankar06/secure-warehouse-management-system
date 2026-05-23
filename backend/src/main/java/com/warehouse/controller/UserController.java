package com.warehouse.controller;

import com.warehouse.dto.LoginRequest;

import com.warehouse.entity.User;

import com.warehouse.repository.UserRepository;

import com.warehouse.security.JwtUtil;

import com.warehouse.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt
        .BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();

    // REGISTER
    @PostMapping("/register")
    public User registerUser(
            @RequestBody User user) {

        return userService.registerUser(user);

    }

    // LOGIN
    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequest request) {

        User user =

                userRepository
                        .findByUsername(
                                request.getUsername()
                        );

        if(user != null &&
                encoder.matches(
                        request.getPassword(),
                        user.getPassword()
                )) {

            return jwtUtil.generateToken(
                    user.getUsername(),
                    user.getRole()
            );

        }

        return "Invalid Login";
    }
}