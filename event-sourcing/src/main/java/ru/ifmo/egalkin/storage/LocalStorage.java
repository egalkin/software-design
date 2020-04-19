package ru.ifmo.egalkin.storage;

import org.springframework.stereotype.Component;
import ru.ifmo.egalkin.event.Statistic;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class LocalStorage {

    private Map<LocalDate, Long> storage;

    public LocalStorage() {
        this.storage = new ConcurrentHashMap<>();
    }

    public void incDate(LocalDate date) {
        long value = storage.getOrDefault(date, 0L);
        storage.put(date, ++value);
    }

    public List<Statistic> get(LocalDate from, LocalDate to) {
        return Stream.iterate(from, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(from, to))
                .map(date -> Statistic.of(date, storage.get(date)))
                .collect(Collectors.toList());
    }
}
