package ru.ifmo.egalkin.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ifmo.egalkin.dao.TaskDao;
import ru.ifmo.egalkin.dao.TaskListDao;
import ru.ifmo.egalkin.logic.DataFilter;
import ru.ifmo.egalkin.model.Task;
import ru.ifmo.egalkin.model.TaskList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
public class TaskListController {
    private final TaskListDao taskListDao;
    private final TaskDao taskDao;

    public TaskListController(TaskListDao taskListDao, TaskDao taskDao) {
        this.taskListDao = taskListDao;
        this.taskDao = taskDao;
    }

    @RequestMapping(value = "/add-taskList", method = RequestMethod.POST)
    public String addTask(@ModelAttribute("taskList") TaskList taskList) {
        taskListDao.addTaskList(taskList);
        return "redirect:/taskLists";
    }

    @RequestMapping(value = "/taskLists", method = RequestMethod.GET)
    public String getTaskList(ModelMap map) {
        prepareModelMap(map, taskListDao.getTasksList());
        return "index";
    }

    @RequestMapping(value = "/taskLists/{id}", method = RequestMethod.GET)
    public String getTaskListTasks(ModelMap map, @PathVariable int id) {
        prepareModelMap(map, taskListDao.getTaskListById(id), taskDao.getTasksByTaskListId(id));
        return "taskList";
    }


    @RequestMapping(value = "/taskLists/{id}/delete", method = RequestMethod.GET)
    public String deleteTaskList(@PathVariable int id) {
        taskListDao.removeTaskList(id);
        return "redirect:/taskLists";
    }

    @RequestMapping(value = "/taskLists/{id}/filter-tasks", method = RequestMethod.GET)
    public String getProducts(@PathVariable int id, @RequestParam String filter, ModelMap map) {
        Optional<DataFilter> dataFilter = DataFilter.getFilterByName(filter);
        dataFilter.ifPresent(value -> prepareModelMap(map, taskListDao.getTaskListById(id), value.filter(id, taskDao)));
        return "taskList";
    }

    private void prepareModelMap(ModelMap map, List<TaskList> taskLists) {
        map.addAttribute("taskLists", taskLists);
        map.addAttribute("taskList", new TaskList());
    }

    private void prepareModelMap(ModelMap map, TaskList taskList, List<Task> tasks) {
        map.addAttribute("currentTaskList", taskList);
        map.addAttribute("currentTasks", tasks);
        map.addAttribute("task", new Task());
    }

}
