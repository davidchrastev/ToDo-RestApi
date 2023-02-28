package com.toDoPage.toDo.pojo;

import java.util.UUID;

public class Task {

    private String id;
    private String info;



    public Task(){
        this.id = UUID.randomUUID().toString();
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
