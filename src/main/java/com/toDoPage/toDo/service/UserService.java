package com.toDoPage.toDo.service;

import com.toDoPage.toDo.pojo.User;
import com.toDoPage.toDo.repository.UserRepository;
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

    public User findByNickName(String userNickName) {
        return userRepository.findByUserNickName(userNickName);
    }

}
