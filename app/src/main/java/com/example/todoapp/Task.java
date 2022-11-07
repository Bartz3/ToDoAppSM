package com.example.todoapp;

import java.util.Date;
import java.util.UUID;

public class Task {
    private UUID id;
    private String name;
    private Date date;
    private boolean done;
    private String details;
    private Category category;

    public Task() {
        id = UUID.randomUUID();
        date = new Date();
        done = false;
        category=Category.HOME;
    }

    public void setCategory(Category category){this.category=category;}

    public Category getCategory(){return category;}

    public String getDetails() {
        return details;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setDate(Date date) {this.date=date;
    }
}
