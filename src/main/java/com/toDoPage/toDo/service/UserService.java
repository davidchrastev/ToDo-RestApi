package com.toDoPage.toDo.service;

import com.toDoPage.toDo.entities.Task;
import com.toDoPage.toDo.entities.User;
import com.toDoPage.toDo.repository.TaskRepository;
import com.toDoPage.toDo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final TaskRepository taskRepository;

  @Autowired
  public UserService(UserRepository userRepository, TaskRepository taskRepository) {
    this.userRepository = userRepository;
    this.taskRepository = taskRepository;
  }


  public List<User> findAllUsers() {
    return userRepository.findAll();
  }

  public List<Task> getTasks(Long id) {
    return userRepository.findById(id)
      .orElseThrow(EntityNotFoundException::new)
      .getTasks();
  }

  public void addTask(Long id, Task task) {
    List<Task> tasks = getTasks(id);

    tasks.add(task);
  }

  public void updateTask(Task task) {
    taskRepository.saveAndFlush(task);
  }
//
//    @Transactional
//    public void registerUser(User user) {
//        String passwordHash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
//        user.setPassword(passwordHash);
//        userRepository.save(user);
//    }
//
//    @Transactional
//    public void overwrite(User user) {
//        userRepository.save(user);
//    }
//
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }
//
//    public User findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//
//    @Transactional
//    public User saveTaskToUser(Long userId, Task task) {
//        Optional<User> user = getTasks(userId);
//        user.ifPresent(u -> {
//            u.getTasks().add(task);
//            task.setUser(u);
//        });
//        return user.orElse(null);
//    }
//
//    @Transactional
//    public void updateTask(Long userId, Task task) {
//        Optional<User> user = getTasks(userId);
//        user.ifPresent(u -> {
//            int index = u.getTasks().indexOf(task);
//            if (index != -1) {
//                u.getTasks().set(index, task);
//            }
//        });
}

//    @Transactional
//    public List<Task> getAllTasksByUserId(Long userId) {
//        return userRepository. findTasksById(userId);
//    }
//
//    public void deleteTaskByUserIdAndTaskId(Long userId, Task task) {
//        List<Task> userTasks = userRepository. findTasksById(userId);
//        userTasks.remove(task);
//    }
//
//    public List<Task> getTasks(Long userId) {
//        return userRepository. findTasksById(userId);
//    }
//
//    public boolean exists(User user) {
//        return findByEmail(user.getEmail()) != null;
//    }
}
