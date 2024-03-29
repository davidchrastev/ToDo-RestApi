package com.toDoPage.toDo.dtos;

import com.toDoPage.toDo.entities.Task;
import com.toDoPage.toDo.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String firstName;

    private String lastName;

    private String email;

    private List<TaskDTO> tasks = new ArrayList<>();

    public static UserDTO convertUser(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.id = user.getId();
        userDTO.firstName = user.getFirstName();
        userDTO.lastName = user.getLastName();
        userDTO.email = user.getEmail();

        for (Task task : user.getTasks()) {
            userDTO.tasks.add(TaskDTO.convertTask(task));
        }

        return userDTO;
    }

}