package com.toDoPage.toDo.controller;

import com.toDoPage.toDo.dtos.TaskDTO;
import com.toDoPage.toDo.dtos.UserDTO;
import com.toDoPage.toDo.entities.Task;
import com.toDoPage.toDo.entities.User;
import com.toDoPage.toDo.service.DeleteTaskService;
import com.toDoPage.toDo.service.TaskService;
import com.toDoPage.toDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {


    //postman http://localhost:8080/tasks

    private final UserService userService;
    private final TaskService taskService;
    private final DeleteTaskService deleteTaskService;

    @Autowired
    public TaskController(UserService userService, TaskService taskService, DeleteTaskService deleteService) {
        this.userService = userService;
        this.taskService = taskService;
        this.deleteTaskService = deleteService;
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

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateTask(@PathVariable Long id, @RequestBody Task task) {
        User user = userService.findUserById(id);

        Optional<Task> optionalTask = user.getTasks().stream()
                .filter(t -> t.getId().equals(task.getId()))
                .findFirst();

        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setDescription(task.getDescription());
            existingTask.setCompletionStatus(task.isCompletionStatus());
            taskService.saveTask(existingTask);

            return new ResponseEntity<>(UserDTO.convertUser(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tasks/delete")
    public ResponseEntity<UserDTO> deleteTask(@RequestBody Map<String, String> loginData) {
        User user = deleteTaskService.delete(loginData);

        return ResponseEntity.ok().body(UserDTO.convertUser(user));
    }

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