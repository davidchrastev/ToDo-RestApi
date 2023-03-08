package com.toDoPage.toDo.controller;

import com.toDoPage.toDo.pojo.Task;
import com.toDoPage.toDo.pojo.User;
import com.toDoPage.toDo.service.TaskService;
import com.toDoPage.toDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class UserController {


    //postman http://localhost:8080/api/tasks/
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @GetMapping("/task/all")
    public ResponseEntity<User> getAllTasks(@PathVariable String userNickName) {
        return new ResponseEntity<>(userService.findByNickName(userNickName), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        Optional<Task> optionalTask = Optional.ofNullable(taskService.findTaskById(id));
        return optionalTask.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save/task/{userNickName}")
    public ResponseEntity<User> saveTaskForUser(@PathVariable String userNickName, @RequestBody Task task) {
        User user = userService.findByNickName(userNickName);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        task.setUser(user);
        Task savedTask = taskService.saveTask(task);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task task) {
        Optional<Task> optionalTask = Optional.ofNullable(taskService.findTaskById(id));
        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setDescription(task.getDescription());
            existingTask.setCompletionStatus(task.isCompletionStatus());
            Task updatedTask = taskService.saveTask(existingTask);
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        Optional<Task> optionalTask = Optional.ofNullable(taskService.findTaskById(id));
        if (optionalTask.isPresent()) {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/sortedByNotCompletedCompilation/all")
    public List<Task> getTasksThatAreNotCompleted() {
        return taskService.findAllTasks().stream().filter(e -> !e.isCompletionStatus())
                .collect(Collectors.toList());
    }

    @GetMapping("/sortedByCompletedCompilation/all")
    public List<Task> getTasksThatAreCompleted() {
        return taskService.findAllTasks().stream().filter(Task::isCompletionStatus)
                .collect(Collectors.toList());
    }

}
