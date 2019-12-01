package ru.ifmo.egalkin.config;

import ru.ifmo.egalkin.dao.TaskDao;
import ru.ifmo.egalkin.dao.TaskListDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InMemoryDaoContextConfiguration {
    @Bean
    public TaskListDao taskListDao() {
        return new TaskListDao();
    }

    @Bean
    public TaskDao taskDao() {
        return new TaskDao();
    }

}
