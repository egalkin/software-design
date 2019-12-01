package ru.ifmo.egalkin.model;

public class Task {
    private int id;
    private int taskListId;
    private String name;
    private boolean isActive;

    public Task() {

    }

    public int getTaskListId() {
        return taskListId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTaskListId(int taskListId) {
        this.taskListId = taskListId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


}
