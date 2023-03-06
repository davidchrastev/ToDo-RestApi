package com.toDoPage.toDo.controller;

import com.toDoPage.toDo.pojo.User;
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
        String nickname = user.getUserNickName();

        User isContained = userService.findByNickName(nickname);
        if (isContained != null) {
            return new ResponseEntity<>(isContained, HttpStatus.CONFLICT);
        }
        userService.registerUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody Map<String, String> loginData) {
        String nickname = loginData.get("nickname");
        String password = loginData.get("password");

        User user = userService.findByNickName(nickname);
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
