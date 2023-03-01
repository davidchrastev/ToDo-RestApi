package com.toDoPage.toDo.repository;

import com.toDoPage.toDo.pojo.Task;
import com.toDoPage.toDo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


}
