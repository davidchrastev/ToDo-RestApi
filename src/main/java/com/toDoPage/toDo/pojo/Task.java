package com.toDoPage.toDo.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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


    public Task() {
        this.id = UUID.randomUUID().toString();
    }

}
