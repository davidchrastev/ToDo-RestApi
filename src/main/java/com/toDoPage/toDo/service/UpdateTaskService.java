package com.toDoPage.toDo.service;

import com.toDoPage.toDo.entities.Task;
import com.toDoPage.toDo.entities.User;
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


    public User updateTask(Long id, Task task) {

        Optional<Task> optionalTask = user.get().getTasks().stream()
                .filter(t -> t.getId().equals(task.getId()))
                .findFirst();

        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setDescription(task.getDescription());
            existingTask.setCompletionStatus(task.isCompletionStatus());
            taskService.saveTask(existingTask);

            return user.get();
        }
        return null;
    }

}