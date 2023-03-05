package com.toDoPage.toDo.repository;

import com.toDoPage.toDo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUserNickName(String userNickName);

}
