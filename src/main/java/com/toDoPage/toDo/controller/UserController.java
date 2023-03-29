package com.toDoPage.toDo.controller;

import com.toDoPage.toDo.dtos.UserDTO;
import com.toDoPage.toDo.entities.User;
import com.toDoPage.toDo.service.AuthService;
import com.toDoPage.toDo.service.RegistrationService;
import com.toDoPage.toDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {


    //postman http://localhost:8080/user

    private final UserService userService;
    private final AuthService authService;
    private final RegistrationService registrationService;
    @Autowired
    public UserController(UserService userService, RegistrationService registrationService, AuthService loginService) {
        this.userService = userService;
        this.registrationService = registrationService;
        this.authService = loginService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody User user) {
        User toRegister = userService.registerUser(user);

        return new ResponseEntity<>(UserDTO.convertUser(toRegister), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody Map<String, String> loginData) {
        if (authService.login(loginData) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(UserDTO.convertUser(authService.login(loginData)), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        return new ResponseEntity<>("Successfully logged out", HttpStatus.OK);
    }



}