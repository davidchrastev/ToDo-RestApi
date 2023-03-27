package com.toDoPage.toDo.service;

import com.toDoPage.toDo.entities.Task;
import com.toDoPage.toDo.entities.User;
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
    public User findUserById(Long id) {
        Optional<User> optionalTask = userRepository.findById(id);
        return optionalTask.orElse(null);
    }

    public void registerUser(User user) {
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
    public void saveTaskToUser(Long userId, Task task) {
        User user = findUserById(userId);
        user.getTasks().add(task);
        task.setUser(user);
    }

    @Transactional
    public void updateTask(Long userId, Task task) {
        User user = findUserById(userId);
        int index = findByIndex(user, task);
        user.getTasks().get(index);
    }


    @Transactional
    public List<Task> getAll(Long id) {
        return userRepository.Tasks(id);
    }

    public int findByIndex(User user, Task task) {
//
//        for (int i = 0; i < getAll(user).size(); i++) {
//            if (getAll(user).get(i).equals(task)) {
//                return i;
//            }
//        }
        return -1;
    }

    public void deleteByUserIdAndTaskId(Long userId, Task task) {
        System.out.println(userRepository.Tasks(userId));
        userRepository.Tasks(userId).remove(task);
    }

    public List<Task> getTasks(Long id) {
        return userRepository.Tasks(id);
    }

    public boolean exists(User user) {
        return findByEmail(user.getEmail()) != null;
    }

}