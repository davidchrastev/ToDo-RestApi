package com.toDoPage.toDo.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    private String id;

    @Column(name = "nickname")
    private String nickname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;




    public User() {
        this.id = UUID.randomUUID().toString();
    }


}
