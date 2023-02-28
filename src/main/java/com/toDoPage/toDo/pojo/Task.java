package com.toDoPage.toDo.pojo;

import java.util.UUID;

public class Task {

    private String id;
    private String description;
    private boolean completionStatus;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Task(){
        this.id = UUID.randomUUID().toString();
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(boolean completionStatus) {
        this.completionStatus = completionStatus;
    }
}
