package com.toDoPage.toDo.controller;

import com.toDoPage.toDo.dtos.TaskDTO;
import com.toDoPage.toDo.dtos.UserDTO;
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
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {


    //postman http://localhost:8080/tasks


    private final UserService userService;
    private final TaskService taskService;

    @Autowired
    public TaskController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/task/all/{id}")
    public ResponseEntity<UserDTO> getAllTasks(@PathVariable Long id) {
        User user = userService.findUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(UserDTO.convertUser(user), HttpStatus.OK);
    }

    @PostMapping("/save/task/{id}")
    public ResponseEntity<UserDTO> saveTask(@PathVariable Long id, @RequestBody Task task) {
        User user = userService.findUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        userService.saveTaskToUser(id, task);

        return new ResponseEntity<>(UserDTO.convertUser(user), HttpStatus.CREATED);
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody Task task) {
//        User user = userService.findUserById(id);
//
//        return new ResponseEntity<>("Successfully saved tasks",HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<UserDTO> deleteTask(@PathVariable Long id) {
////        System.out.println(userService.getAll(id));
//        User user = userService.findUserById(id);
//
//        return new ResponseEntity<>(UserDTO.convertUser(user), HttpStatus.NO_CONTENT);
//    }

    @GetMapping("/sortedByNotCompletedCompilation/all/{id}")
    public ResponseEntity<UserDTO> getTasksThatAreNotCompleted(@PathVariable Long id) {
        User user = userService.findUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserDTO userDTO = UserDTO.convertUser(user);

        userDTO.getTasks().stream().filter(e -> !e.isCompletionStatus()).collect(Collectors.toList());

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/sortedByCompletedCompilation/all/{id}")
    public ResponseEntity<UserDTO> getTasksThatAreCompleted(@PathVariable Long id) {
        User user = userService.findUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserDTO userDTO = UserDTO.convertUser(user);

        userDTO.getTasks().stream().filter(TaskDTO::isCompletionStatus).collect(Collectors.toList());

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }


}