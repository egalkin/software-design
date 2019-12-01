package ru.ifmo.egalkin.logic;

import ru.ifmo.egalkin.dao.TaskDao;
import ru.ifmo.egalkin.model.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class DataFilter {
    private static Map<String, DataFilter> filters = createFiltersMap();

    private static HashMap<String, DataFilter> createFiltersMap() {
        HashMap<String, DataFilter> filters = new HashMap<>();
        filters.put("all", new AllFilter());
        filters.put("active", new ActiveFilter());
        return filters;
    }

    public abstract List<Task> filter(int id, TaskDao taskDao);

    private static class AllFilter extends DataFilter {
        public List<Task> filter(int id, TaskDao taskDao) {
            return taskDao.getTasksByTaskListId(id);
        }
    }

    private static class ActiveFilter extends DataFilter {
        public List<Task> filter(int id, TaskDao productDao) {
            return productDao.getActiveTasksByTaskListId(id);
        }
    }

    public static Optional<DataFilter> getFilterByName(String name) {
        return Optional.ofNullable(filters.get(name));
    }
}
