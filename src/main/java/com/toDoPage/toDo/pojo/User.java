package com.toDoPage.toDo.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    private String id;

    @NotBlank(message = "Nickname cannot be blank")
    @NonNull
    @Column(name = "nickname", nullable = false)
    private String nickname;

    @NotBlank(message = "Email cannot be blank")
    @NonNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "users")
    private List<Task> tasks;




    public User() {
        this.id = UUID.randomUUID().toString();
    }


}

