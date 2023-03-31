package com.toDoPage.toDo.service;

import com.toDoPage.toDo.entities.Task;
import com.toDoPage.toDo.entities.User;
import com.toDoPage.toDo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final TaskService taskService;
    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TaskService taskService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.taskService = taskService;
    }


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }



    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void registerUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    public void overwrite(User user) {
        userRepository.save(user);

    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User saveTaskToUser(Long userId, Task task) {
        Optional<User> optionalUser = findUserById(userId);

        User user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
        user.getTasks().add(task);
        task.setUser(user);

        return user;
    }

    @Transactional
    public User updateTask(Long id, Task task) {
        Optional<User> userOptional = findUserById(id);

        return userOptional.map(u -> {
            Optional<Task> optionalTask = u.getTasks().stream()
                    .filter(t -> t.getId().equals(task.getId()))
                    .findFirst();

            if (optionalTask.isPresent()) {
                Task existingTask = optionalTask.get();
                existingTask.setDescription(task.getDescription());
                existingTask.setCompletionStatus(task.isCompletionStatus());
                taskService.saveTask(existingTask);
            }
            return u;
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public User deleteTaskFromUser(Map<String, String> userIdTaskId) {
        String userIdString = userIdTaskId.get("userId");
        String taskIdString = userIdTaskId.get("taskId");

        long userId = convertToLong(userIdString, "Invalid user ID: " + userIdString);
        long taskId = convertToLong(taskIdString, "Invalid task ID: " + taskIdString);

        User user = findUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Task> tasks = user.getTasks();

        boolean taskRemoved = tasks.removeIf(task -> task.getId() == taskId);
        if (!taskRemoved) {
            throw new RuntimeException("Task not found");
        }

        overwrite(user);

        return user;
    }
    private long convertToLong(String string, String errorMessage) {
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException e) {
            throw new RuntimeException(errorMessage);
        }
    }


    public List<Task> getTasks(Long id) {
        return userRepository.Tasks(id);
    }

    public boolean exists(User user) {
        return findByEmail(user.getEmail()) != null;
    }

}