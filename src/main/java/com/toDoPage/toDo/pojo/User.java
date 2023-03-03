package com.toDoPage.toDo.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
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




    public User() {
        this.id = UUID.randomUUID().toString();
    }


}
