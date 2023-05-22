package com.toDoPage.toDo.service.task_service;

import com.toDoPage.toDo.entities.Task;
import com.toDoPage.toDo.entities.User;
import com.toDoPage.toDo.service.user_service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DeleteTaskService {

    private final UserService userService;

    public DeleteTaskService(UserService userService) {
        this.userService = userService;
    }


    @Transactional
    public User deleteTaskFromUser(Map<String, String> userIdTaskId) {
        String userIdString = userIdTaskId.get("userId");
        String taskIdString = userIdTaskId.get("taskId");

        long userId = convertToLong(userIdString, "Invalid user ID: " + userIdString);
        long taskId = convertToLong(taskIdString, "Invalid task ID: " + taskIdString);

        User user = userService.findUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Task> tasks = user.getTasks();

        boolean taskRemoved = tasks.removeIf(task -> task.getId() == taskId);
        if (!taskRemoved) {
            throw new RuntimeException("Task not found");
        }

        userService.overwrite(user);

        return user;
    }


    private long convertToLong(String string, String errorMessage) {
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException e) {
            throw new RuntimeException(errorMessage);
        }
    }
}