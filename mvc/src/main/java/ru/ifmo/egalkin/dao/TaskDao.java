package ru.ifmo.egalkin.dao;

import ru.ifmo.egalkin.model.Task;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TaskDao {
    private final AtomicInteger lastTaskId = new AtomicInteger(0);
    private final LinkedHashMap<Integer, Task> tasks = new LinkedHashMap<>();

    public int addTask(int taskListId, Task task) {
        int id = lastTaskId.incrementAndGet();
        task.setId(id);
        task.setTaskListId(taskListId);
        task.setActive(true);
        tasks.put(id, task);
        return id;
    }

    public void updateTask(int taskListId, int taskId, Task task) {
        task.setTaskListId(taskListId);
        tasks.put(taskId, task);
    }

    public void removeTask(int taskId) {
        tasks.remove(taskId);
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public List<Task> getAllTasks() {
        return List.copyOf(tasks.values());
    }

    public List<Task> getTasksByTaskListId(int taskListId) {
        return tasks.values().stream().filter(task -> task.getTaskListId() == taskListId).collect(Collectors.toList());
    }

    public List<Task> getActiveTasksByTaskListId(int taskListId) {
        return getTasksByTaskListId(taskListId).stream().filter(Task::isActive).collect(Collectors.toList());
    }
}
