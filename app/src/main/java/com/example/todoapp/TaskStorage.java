package com.example.todoapp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class TaskStorage {
    private static final TaskStorage taskStorage = new TaskStorage();
    private final List<Task> tasks;

    public static TaskStorage getInstance() {
        return taskStorage;
    }

//    public Task at(UUID id) {
//        return tasks.stream().filter(e->e.GetId().equals(id)).findFirst().get();
//    }
public Task at(UUID id){
    Iterator<Task> iterator=tasks.iterator();
    while(iterator.hasNext()){
        Task task = iterator.next();
        if(task.getId().equals(id)){
            return task;
        }
    }
    return null;
}

    public List<Task> getTasks(){
        return tasks;
    }

    public TaskStorage() {
        tasks = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Task task = new Task();
            task.setName("Pilne zadanie numer: " + i);
            task.setDone(i % 3 == 0);
            task.setDetails("abcdefg");
            tasks.add(task);
        }
    }
}
