package com.toDoPage.toDo.controller;

import com.toDoPage.toDo.dtos.TaskDTO;
import com.toDoPage.toDo.dtos.UserDTO;
import com.toDoPage.toDo.entities.User;
import com.toDoPage.toDo.service.TaskService;
import com.toDoPage.toDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {


    //postman http://localhost:8080/tasks

    private final UserService userService;
    private final TaskService taskService;


    @Autowired
    public TaskController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getAllTasks(@PathVariable Long id) {
        User user = userService.findUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    @PostMapping("/save/task/{id}")
    public ResponseEntity<UserDTO> saveTask(@PathVariable Long id, @RequestBody Task task) {
        User userConvert = userService.saveTaskToUser(id, task);
        return new ResponseEntity<>(UserDTO.convertUser(userConvert), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateTask(@PathVariable Long id, @RequestBody Task task) {
        User user = userService.updateTask(id, task);
        return new ResponseEntity<>(UserDTO.convertUser(user), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserDTO> saveTask(@PathVariable Long id, @RequestBody Task task) {
        User user = userService.findUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        userService.saveTaskToUser(id, task);

        return new ResponseEntity<>(UserDTO.convertUser(user), HttpStatus.CREATED);
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
<<<<<<< HEAD
        User user = deleteTaskService.delete(loginData);

=======
        User user = userService.deleteTaskFromUser(loginData);
>>>>>>> parent of e1bdfe8 (Cleaner Code)
        return ResponseEntity.ok().body(UserDTO.convertUser(user));
    }

    @GetMapping("/sortedByNotCompletedCompilation/all/{id}")
    public ResponseEntity<UserDTO> getTasksThatAreNotCompleted(@PathVariable Long id) {
<<<<<<< HEAD
        User user = userService.findUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserDTO userDTO = UserDTO.convertUser(user);

        userDTO.getTasks().stream().filter(e -> !e.isCompletionStatus()).collect(Collectors.toList());
=======
        Optional<User> user = userService.findUserById(id);

        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserDTO userDTO = UserDTO.convertUser(user.get());
>>>>>>> parent of e1bdfe8 (Cleaner Code)

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/sortedByCompletedCompilation/all/{id}")
    public ResponseEntity<UserDTO> getTasksThatAreCompleted(@PathVariable Long id) {
<<<<<<< HEAD
        User user = userService.findUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserDTO userDTO = UserDTO.convertUser(user);

        userDTO.getTasks().stream().filter(TaskDTO::isCompletionStatus).collect(Collectors.toList());
=======
        Optional<User> user = userService.findUserById(id);

        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserDTO userDTO = UserDTO.convertUser(user.get());
>>>>>>> parent of e1bdfe8 (Cleaner Code)

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }


}