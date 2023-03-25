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

    public void register(User user) {
        String email = user.getEmail();
        User isContained = userService.findByEmail(email);

        if (isContained == null) {
            userService.registerUser(user);
        }
    }
}
