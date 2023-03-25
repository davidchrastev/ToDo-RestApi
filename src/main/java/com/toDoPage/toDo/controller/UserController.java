package com.toDoPage.toDo.controller;

import com.toDoPage.toDo.dtos.UserDTO;
import com.toDoPage.toDo.entities.User;
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
    private final RegistrationService registrationService;
    @Autowired
    public UserController(UserService userService, RegistrationService registrationService) {
        this.userService = userService;
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody User user) {
        User check = userService.findByEmail(user.getEmail());

        if (check != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        registrationService.register(user);

        return new ResponseEntity<>(UserDTO.convertUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        User user = userService.findByEmail(email);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!user.getPassword().equals(password)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        System.out.println(user.getEmail());

        return new ResponseEntity<>(user.getFirstName() + " " + user.getLastName(), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        return new ResponseEntity<>("Successfully logged out", HttpStatus.OK);
    }



}