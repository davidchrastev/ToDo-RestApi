package com.toDoPage.toDo.controller;

import com.toDoPage.toDo.dtos.UserDTO;
import com.toDoPage.toDo.entities.User;
import com.toDoPage.toDo.service.LoginService;
import com.toDoPage.toDo.service.RegistrationService;
import com.toDoPage.toDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {


    //postman http://localhost:8080/user

    private final UserService userService;
    private final LoginService loginService;
    private final RegistrationService registrationService;
    @Autowired
    public UserController(UserService userService, RegistrationService registrationService, LoginService loginService) {
        this.userService = userService;
        this.registrationService = registrationService;
        this.loginService = loginService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody User user) {

        if (userService.exists(user)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        registrationService.register(user);
        return new ResponseEntity<>(UserDTO.convertUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody Map<String, String> loginData) {
        if (loginService.login(loginData) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(UserDTO.convertUser(loginService.login(loginData)), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        return new ResponseEntity<>("Successfully logged out", HttpStatus.OK);
    }



}