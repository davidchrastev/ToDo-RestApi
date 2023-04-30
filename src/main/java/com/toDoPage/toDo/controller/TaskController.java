package com.toDoPage.toDo.controller;

import com.toDoPage.toDo.dtos.UserDTO;
import com.toDoPage.toDo.entities.Task;
import com.toDoPage.toDo.entities.User;
import com.toDoPage.toDo.service.task_service.AddTaskService;
import com.toDoPage.toDo.service.task_service.DeleteTaskService;
import com.toDoPage.toDo.service.task_service.UpdateTaskService;
import com.toDoPage.toDo.service.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {


    //postman http://localhost:8080/user/tasks
    // tasks/delete


    private final UserService userService;
    private final DeleteTaskService deleteTask;
    private final UpdateTaskService updateTask;
    private final AddTaskService addTask;


    @Autowired
    public TaskController(UserService userService, DeleteTaskService deleteTaskService, UpdateTaskService updateTask, AddTaskService addTask) {
        this.userService = userService;
        this.deleteTask = deleteTaskService;
        this.updateTask = updateTask;
        this.addTask = addTask;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getAllTasks(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(UserDTO.convertUser(user.get()), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserDTO> saveTask(@PathVariable Long id, @RequestBody Task task) {
        User userConvert = addTask.saveTaskToUser(id, task);
        return new ResponseEntity<>(UserDTO.convertUser(userConvert), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateTask(@PathVariable Long id, @RequestBody Task task) {
        User user = updateTask.updateTask(id, task);
        return new ResponseEntity<>(UserDTO.convertUser(user), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<UserDTO> deleteTask(@RequestBody Map<String, String> loginData) {
        User user = deleteTask.deleteTaskFromUser(loginData);
        return ResponseEntity.ok().body(UserDTO.convertUser(user));
    }

//    @GetMapping("/sortedByNotCompletedCompilation/all/{id}")
//    public ResponseEntity<UserDTO> getTasksThatAreNotCompleted(@PathVariable Long id) {
//        Optional<User> user = userService.findUserById(id);
//
//        if (user.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        UserDTO userDTO = UserDTO.convertUser(user.get());
//
//        return new ResponseEntity<>(userDTO, HttpStatus.OK);
//    }
//
//    @GetMapping("/sortedByCompletedCompilation/all/{id}")
//    public ResponseEntity<UserDTO> getTasksThatAreCompleted(@PathVariable Long id) {
//        Optional<User> user = userService.findUserById(id);
//
//        if (user.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        UserDTO userDTO = UserDTO.convertUser(user.get());
//
//        return new ResponseEntity<>(userDTO, HttpStatus.OK);
//    }


}