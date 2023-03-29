package com.toDoPage.toDo.service;

import com.toDoPage.toDo.entities.Task;
import com.toDoPage.toDo.entities.User;
import com.toDoPage.toDo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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


    @Transactional
    public User findUserById(Long id) {
        Optional<User> optionalTask = userRepository.findById(id);
        return optionalTask.orElse(null);
    }

    @Transactional
    public User registerUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return user;
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
        User user = findUserById(userId);
        user.getTasks().add(task);
        task.setUser(user);
        return user;
    }

    @Transactional
    public User updateTask(Long id, Task task) {
        User user = findUserById(id);

        Optional<Task> optionalTask = user.getTasks().stream()
                .filter(t -> t.getId().equals(task.getId()))
                .findFirst();

        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setDescription(task.getDescription());
            existingTask.setCompletionStatus(task.isCompletionStatus());
            taskService.saveTask(existingTask);

            return user;
        } else {
            return null;
        }
    }

    public User deleteTaskFromUser(@RequestBody Map<String, String> userIdTaskId) {
        String userId = userIdTaskId.get("userId");
        String taskId = userIdTaskId.get("taskId");

        User user = findUserById(Long.valueOf(userId));

        Task taskToDelete = user.getTasks().stream()
                .filter(task -> task.getId().equals(Long.valueOf(taskId)))
                .findFirst()
                .orElseThrow();

        taskService.deleteTask(taskToDelete.getId());

        user.getTasks().remove(taskToDelete);

        overwrite(user);

        return user;
    }


    public List<Task> getTasks(Long id) {
        return userRepository.Tasks(id);
    }

    public boolean exists(User user) {
        return findByEmail(user.getEmail()) != null;
    }

}