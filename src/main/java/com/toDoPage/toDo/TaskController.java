package com.toDoPage.toDo;

import com.toDoPage.toDo.pojo.Task;
import com.toDoPage.toDo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {


    //postman http://localhost:8080/api/tasks/
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/task/all")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save/task")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = taskRepository.save(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task task) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setDescription(task.getDescription());
            existingTask.setCompletionStatus(task.isCompletionStatus());
            Task updatedTask = taskRepository.save(existingTask);
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            taskRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/sortedByNotCompletedCompilation/all")
    public List<Task> getTasksThatAreNotCompleted() {
        return taskRepository.findAll().stream().filter(e -> !e.isCompletionStatus())
                .collect(Collectors.toList());
    }

    @GetMapping("/sortedByCompletedCompilation/all")
    public List<Task> getTasksThatAreCompleted() {
        return taskRepository.findAll().stream().filter(Task::isCompletionStatus)
                .collect(Collectors.toList());
    }

}
