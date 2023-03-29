package com.toDoPage.toDo.exception;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(Long userId, Long taskId) {
        super("The task " + taskId + " with user id: '" + userId + " does not exist in our records");
    }
}
