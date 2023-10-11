package com.example.app_tasks.model;

import java.io.Serializable;

public class Task implements Serializable { //Serializable possibilita o envio de dados entre activitys
    private Long id;
    private String nameTask;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }
}
