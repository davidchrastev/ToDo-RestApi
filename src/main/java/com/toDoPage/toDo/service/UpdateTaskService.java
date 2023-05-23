package com.toDoPage.toDo.service;

import com.toDoPage.toDo.dtos.UserDTO;
import com.toDoPage.toDo.entities.Task;
import com.toDoPage.toDo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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



    public User updateTask(Long id, Task task) {
        User user = userService.findUserById(id);

        Optional<Task> optionalTask = user.getTasks().stream()
                .filter(t -> t.getId().equals(task.getId()))
                .findFirst();

        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setDescription(task.getDescription());
            existingTask.setCompletionStatus(task.isCompletionStatus());
            taskService.saveTask(existingTask);

            return user;
        } else {
            return null;
        }
    }
}