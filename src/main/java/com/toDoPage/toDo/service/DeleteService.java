package com.toDoPage.toDo.service;

import com.toDoPage.toDo.entities.Task;
import com.toDoPage.toDo.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
public class DeleteService {

    private final UserService userService;
    private final TaskService taskService;

    public DeleteService(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }


    public User delete(@RequestBody Map<String, String> userIdTaskId) {
        String userId = userIdTaskId.get("userId");
        String taskId = userIdTaskId.get("taskId");

        User user = userService.findUserById(Long.valueOf(userId));

        Task taskToDelete = user.getTasks().stream()
                .filter(task -> task.getId().equals(Long.valueOf(taskId)))
                .findFirst()
                .orElseThrow();

        taskService.deleteTask(taskToDelete.getId());

        user.getTasks().remove(taskToDelete);

        userService.registerUser(user);

        return user;
    }
}
