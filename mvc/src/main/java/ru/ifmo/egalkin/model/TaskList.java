package ru.ifmo.egalkin.model;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private int id;
    private String name;

    public TaskList() {
    }

    public TaskList(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
