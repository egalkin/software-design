package ru.ifmo.egalkin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ifmo.egalkin.dao.TaskDao;
import ru.ifmo.egalkin.model.Task;
import ru.ifmo.egalkin.model.TaskList;

@Controller
public class TaskController {
    private final TaskDao taskDao;

    public TaskController(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @RequestMapping(value = "/taskLists/{id}/add-task", method = RequestMethod.POST)
    public String addTask(@PathVariable int id, @ModelAttribute("task") Task task) {
        taskDao.addTask(id, task);
        return "redirect:/taskLists/{id}";
    }

    @RequestMapping(value = "/taskLists/{id}/task/{taskId}", method = RequestMethod.GET)
    public String getTask(ModelMap map, @PathVariable int id, @PathVariable int taskId) {
        prepareModelMap(map, id, taskDao.getTaskById(taskId));
        return "task";
    }

    @RequestMapping(value = "/taskLists/{id}/task/{taskId}", method = RequestMethod.POST)
    public String updateTask(@PathVariable int id, @PathVariable int taskId, @ModelAttribute("task") Task task) {
        taskDao.updateTask(id, taskId, task);
        return "redirect:/taskLists/{id}/task/{taskId}";
    }

    @RequestMapping(value = "/taskLists/{id}/task/{taskId}/delete", method = RequestMethod.GET)
    public String deleteTaskList(@PathVariable int id, @PathVariable int taskId) {
        taskDao.removeTask(taskId);
        return "redirect:/taskLists/{id}";
    }


    private void prepareModelMap(ModelMap map, int taskListId, Task task) {
        map.addAttribute("task", task);
        map.addAttribute("currentTaskListId", taskListId);
    }

}
