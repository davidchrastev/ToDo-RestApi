package com.toDoPage.toDo.dtos;

import com.toDoPage.toDo.entities.Task;
import com.toDoPage.toDo.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private String description;

    private boolean completionStatus;


    public static TaskDTO convertTask(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.description = task.getDescription();
        taskDTO.completionStatus = task.isCompletionStatus();

        return taskDTO;
    }
}