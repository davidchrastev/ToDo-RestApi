package com.toDoPage.toDo.service.user_service;

import com.toDoPage.toDo.entities.Task;
import com.toDoPage.toDo.entities.User;
import com.toDoPage.toDo.repository.UserRepository;
import com.toDoPage.toDo.service.task_service.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
    public void overwrite(User user) {
        userRepository.save(user);

    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}