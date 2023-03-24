package com.toDoPage.toDo.controller;

import com.toDoPage.toDo.entities.Task;
import com.toDoPage.toDo.entities.User;
import com.toDoPage.toDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {


    //postman http://localhost:8080/api/user
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        String email = user.getEmail();

        User isContained = userService.findByUserEmail(email);
        if (isContained != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userService.registerUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);


    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody Map<String, String> loginData) {
        String userEmail = loginData.get("email");
        String password = loginData.get("password");

        User user = userService.findByUserEmail(userEmail);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!user.getPassword().equals(password)) {
            return new ResponseEntity<>(user, HttpStatus.UNAUTHORIZED);
        }


        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        return new ResponseEntity<>("Successfully logged out", HttpStatus.OK);
    }

}