package com.toDoPage.toDo.controller;

import com.toDoPage.toDo.pojo.User;
import com.toDoPage.toDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {


    //postman http://localhost:8080/api/user
    @Autowired
    UserService userService;


    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        Optional<User> optionalTask = Optional.ofNullable(userService.findUserById(id));
        return optionalTask.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        User isContained = userService.findUserById(user.getNickname());

        if (isContained != null) {
            return new ResponseEntity<>("User with that username already exists", HttpStatus.CREATED);
        } else {
            userService.registerUser(user);
            return new ResponseEntity<>("Successfully created user with nickname " + user.getNickname(), HttpStatus.CREATED);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User isContained = userService.findUserById(user.getNickname());

        if (isContained == null) {
            return new ResponseEntity<>("Wrong username or wrong password", HttpStatus.CREATED);
        } else {
            userService.registerUser(user);
            return new ResponseEntity<>("Successfully logged  " + user.getNickname(), HttpStatus.CREATED);
        }
    }

}
