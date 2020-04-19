package ru.ifmo.egalkin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ifmo.egalkin.event.EventHandler;
import ru.ifmo.egalkin.system.ReporterSystem;

import java.time.Clock;

@Configuration
public class Config {

    @Autowired
    private ReporterSystem reporterSystem;

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public EventHandler eventHandler() {
        EventHandler handler = new EventHandler();
        handler.addListener(reporterSystem);
        return handler;
    }
}