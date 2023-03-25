package com.toDoPage.toDo.service;

import com.toDoPage.toDo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserService userService;

    @Autowired
    public RegistrationService(UserService userService) {
        this.userService = userService;
    }

    public boolean register(User user) {
        if (userService.exists(user)) {
            return true;
        } else {
            userService.registerUser(user);
            return false;
        }
    }
}
