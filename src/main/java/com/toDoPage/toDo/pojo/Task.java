package com.toDoPage.toDo.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Entity
@Table(name = "new_table")
@Getter
@Setter
public class Task {
    @Id
    private String id;

    @Column(name = "description")
    private String description;
    @Column(name = "completionStatus")
    private boolean completionStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task() {
        this.id = UUID.randomUUID().toString();
    }

}
