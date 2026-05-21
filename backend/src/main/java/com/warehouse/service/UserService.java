package com.warehouse.service;

import com.warehouse.entity.User;
import com.warehouse.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // PASSWORD ENCODER
    private BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();

    // REGISTER USER
    public User registerUser(User user) {

        // CHECK EXISTING EMAIL
        User existingUser =
                userRepository.findByEmail(user.getEmail());

        if(existingUser != null) {

            return null;

        }

        // ENCRYPT PASSWORD
        user.setPassword(
                encoder.encode(user.getPassword())
        );

        return userRepository.save(user);

    }

    // LOGIN USER
    public User loginUser(String email, String password) {

        User user =
                userRepository.findByEmail(email);

        // VERIFY PASSWORD
        if(user != null &&
                encoder.matches(
                        password,
                        user.getPassword()
                )) {

            return user;

        }

        return null;
    }
}