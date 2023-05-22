package com.toDoPage.toDo.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "task")
@Getter
@Setter
@AllArgsConstructor
public class Task {

    public Task() {

    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Description cannot be blank")
    @NonNull
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private boolean completionStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;


}