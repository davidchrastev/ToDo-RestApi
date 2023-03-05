package com.toDoPage.toDo.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;


@Entity
@Table(name = "new_table")
@Getter
@Setter
public class Task {
    @Id
    private String id;
    @NotBlank(message = "Description cannot be blank")
    @NonNull
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "completionStatus", nullable = false)
    private boolean completionStatus;



    public Task() {
        this.id = UUID.randomUUID().toString();
    }

}
