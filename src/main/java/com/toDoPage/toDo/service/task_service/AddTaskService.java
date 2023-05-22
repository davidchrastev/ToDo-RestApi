package com.toDoPage.toDo.service.task_service;

import com.toDoPage.toDo.entities.Task;
import com.toDoPage.toDo.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddTaskService {
    private final UserService userService;
    @Autowired
    public AddTaskService(UserService userService) {
        this.userService = userService;
    }
    @Transactional
    public User saveTaskToUser(Long userId, Task task) {
        Optional<User> optionalUser = userService.findUserById(userId);

        User user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
        user.getTasks().add(task);
        task.setUser(user);

        return user;
    }
}
