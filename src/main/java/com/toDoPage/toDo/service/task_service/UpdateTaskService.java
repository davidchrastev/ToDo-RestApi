package com.toDoPage.toDo.service.task_service;

import com.toDoPage.toDo.entities.Task;
import com.toDoPage.toDo.entities.User;
import com.toDoPage.toDo.service.user_service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateTaskService {

    private final UserService userService;
    private final TaskService taskService;


    @Autowired
    public UpdateTaskService(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }


    @Transactional
    public User updateTask(Long id, Task task) {
        Optional<User> userOptional = userService.findUserById(id);

        return userOptional.map(u -> {
            Optional<Task> optionalTask = u.getTasks().stream()
                    .filter(t -> t.getId().equals(task.getId()))
                    .findFirst();

            if (optionalTask.isPresent()) {
                Task existingTask = optionalTask.get();
                existingTask.setDescription(task.getDescription());
                existingTask.setCompletionStatus(task.isCompletionStatus());
                taskService.saveTask(existingTask);
            }
            return u;
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

}