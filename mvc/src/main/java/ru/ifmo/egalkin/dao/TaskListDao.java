package ru.ifmo.egalkin.dao;

import ru.ifmo.egalkin.model.TaskList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskListDao {
    private final AtomicInteger lastTaskListId = new AtomicInteger(0);
    private final LinkedHashMap<Integer, TaskList> taskLists = new LinkedHashMap<>();

    public int addTaskList(TaskList taskList) {
        int id = lastTaskListId.incrementAndGet();
        taskList.setId(id);
        this.taskLists.put(id, taskList);
        return id;
    }

    public TaskList getTaskListById(int id) {
        return taskLists.get(id);
    }

    public List<TaskList> getTasksList() {
        return List.copyOf(taskLists.values());
    }

    public void removeTaskList(int id) {
        taskLists.remove(id);
    }

}
