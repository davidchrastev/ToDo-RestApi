package com.toDoPage.toDo.controller;

import com.toDoPage.toDo.dtos.TaskDTO;
import com.toDoPage.toDo.dtos.UserDTO;
import com.toDoPage.toDo.entities.Task;
import com.toDoPage.toDo.entities.User;
import com.toDoPage.toDo.service.DeleteTaskService;
import com.toDoPage.toDo.service.UpdateTaskService;
import com.toDoPage.toDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    private final UserService userService;
    private final DeleteTaskService deleteTaskService;
    private final UpdateTaskService updateTaskService;

    @Autowired
    public TaskController(UserService userService, DeleteTaskService deleteTaskService, UpdateTaskService updateTaskService) {
        this.userService = userService;
        this.deleteTaskService = deleteTaskService;
        this.updateTaskService = updateTaskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Task>> getAllTasks(@PathVariable Long id) {
        return ResponseEntity.of(Optional.ofNullable(userService.getTasks(id)));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> saveTask(@PathVariable Long id, @RequestBody Task task) {
        userService.addTask(id, task);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateTask(@PathVariable Long id, @RequestBody Task task) {
        User user = updateTaskService.updateTask(id, task);

        if (user != null) {
            return new ResponseEntity<>(UserDTO.convertUser(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<UserDTO> deleteTask(@RequestBody Map<String, String> loginData) {
        User user = deleteTaskService.delete(loginData);

        return ResponseEntity.ok().body(UserDTO.convertUser(user));
    }

    @GetMapping("/sortedByNotCompletedCompilation/all/{id}")
    public ResponseEntity<UserDTO> getTasksThatAreNotCompleted(@PathVariable Long id) {
        Optional<User> user = userService.getTasks(id);

        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserDTO userDTO = UserDTO.convertUser(user.get());
        userDTO.setTasks(userDTO.getTasks().stream().filter(e -> !e.isCompletionStatus()).collect(Collectors.toList()));

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

//    @GetMapping("/sortedByCompletedCompilation/all/{id}")
//    public ResponseEntity<UserDTO> getTasksThatAreCompleted(@PathVariable Long id) {
//        Optional<User> user = userService.getTasks(id);
//
//        if (user.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        UserDTO userDTO = UserDTO.convertUser(user.get());
//        userDTO.setTasks(userDTO.getTasks().stream().filter(TaskDTO::isCompletionStatus).collect(Collectors.toList()));
//
//        return new ResponseEntity<>(userDTO, HttpStatus.OK);
//    }
}
