package com.toDoPage.toDo.controller;

import com.toDoPage.toDo.entities.Task;
import com.toDoPage.toDo.entities.User;
import com.toDoPage.toDo.service.TaskService;
import com.toDoPage.toDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {


    //postman http://localhost:8080/tasks
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping("/task/all/{id}")
    public ResponseEntity<User> getAllTasks(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        Optional<Task> optionalTask = Optional.ofNullable(taskService.findTaskById(id));
        return optionalTask.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save/task/{id}")
    public ResponseEntity<User> saveTask(@PathVariable Long id, @RequestBody Task task) {
        userService.saveTaskToUser(id, task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody Task task) {
        User user = userService.findUserById(id);

        return new ResponseEntity<>("Successfully saved tasks",HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id, @RequestBody Task task) {
        User user = userService.findUserById(id);
        userService.deleteTask(id, task);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/sortedByNotCompletedCompilation/all/{id}")
    public List<String> getTasksThatAreNotCompleted(@PathVariable Long id) {
        User user = userService.findUserById(id);
        List<String> tasks = new ArrayList<>();
        user.getTasks()
                .forEach(e -> tasks.add(e.getDescription() + " " +  e.isCompletionStatus()));
        return tasks;
    }

    @GetMapping("/sortedByCompletedCompilation/all/{id}")
    public List<String> getTasksThatAreCompleted(@PathVariable Long id) {
        User user = userService.findUserById(id);
        List<String> tasks = new ArrayList<>();
        user.getTasks()
                .forEach(e -> tasks.add(e.getDescription() + " " +  e.isCompletionStatus()));
        return tasks;
    }


}