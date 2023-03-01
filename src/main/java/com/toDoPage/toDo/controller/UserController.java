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
    public ResponseEntity<User> register(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        Optional<User> optionalNickname = Optional.ofNullable(userService.findUserById(user.getId()));
        return optionalNickname.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
