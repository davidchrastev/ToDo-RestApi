package com.toDoPage.toDo.service;

import com.toDoPage.toDo.pojo.Task;
import com.toDoPage.toDo.pojo.User;
import com.toDoPage.toDo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    @Transactional
    public User findUserById(String id) {
        Optional<User> optionalTask = userRepository.findById(id);
        return optionalTask.orElse(null);
    }

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public User findByNickName(String nickname) {
        return userRepository.findByUserNickName(nickname);
    }

    @Transactional
    public void saveTaskToUser(String userId, Task task) {
        User user = findUserById(userId);
        user.getTasks().add(task);
        task.setUser(user);
        userRepository.save(user);
    }

}