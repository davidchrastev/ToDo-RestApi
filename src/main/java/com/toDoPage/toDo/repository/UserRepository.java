package com.toDoPage.toDo.repository;

import com.toDoPage.toDo.entities.Task;
import com.toDoPage.toDo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    List<Task> Tasks(Long id);
}