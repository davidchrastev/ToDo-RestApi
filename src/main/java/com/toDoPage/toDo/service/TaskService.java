package com.toDoPage.toDo.service;

import com.toDoPage.toDo.entities.Task;
import com.toDoPage.toDo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Task findTaskById(String id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }
}