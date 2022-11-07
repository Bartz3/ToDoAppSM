package com.example.todoapp;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class    TaskStorage {
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

            //task.setDetails("Treść zadania");
            task.setName("Nazwa zadania");

            if(i%3==0){
                task.setCategory(Category.STUDIES);
                task.setDetails("STUDIA");
            }else {task.setCategory(Category.HOME);
            task.setDetails("HOME");}
            tasks.add(task);
        }
    }
}
