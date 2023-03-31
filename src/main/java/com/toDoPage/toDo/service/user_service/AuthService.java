package com.toDoPage.toDo.service.user_service;

import com.toDoPage.toDo.entities.User;
import com.toDoPage.toDo.service.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private final UserService userService;

    @Autowired
    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public Optional<User> login(Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        Optional<User> user = userService.findByEmail(email);

        user.ifPresent(value -> BCrypt.checkpw(password, value.getPassword()));

        return user;
    }
}
