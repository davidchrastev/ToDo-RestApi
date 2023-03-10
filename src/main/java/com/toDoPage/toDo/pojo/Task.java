package com.toDoPage.toDo.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Description cannot be blank")
    @NonNull
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private boolean completionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;




    public Task() {

    }

}