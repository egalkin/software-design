package ru.ifmo.egalkin.system;

import org.springframework.stereotype.Component;
import ru.ifmo.egalkin.event.EventListener;
import ru.ifmo.egalkin.event.Statistic;
import ru.ifmo.egalkin.event.model.ClientEntryEvent;
import ru.ifmo.egalkin.event.model.Event;
import ru.ifmo.egalkin.storage.LocalStorage;

import java.time.LocalDate;
import java.util.List;

@Component
public class ReporterSystem implements EventListener {
    private final LocalStorage localStorage;

    public ReporterSystem(LocalStorage localStorage) {
        this.localStorage = localStorage;
    }

    @Override
    public void handle(long accountId, Event event) {
        if (event instanceof ClientEntryEvent) {
            localStorage.incDate(((ClientEntryEvent) event).getEntryTime().toLocalDate());
        }
    }

    public List<Statistic> getDailyStat(LocalDate from, LocalDate to) {
        return localStorage.get(from, to);
    }
}
