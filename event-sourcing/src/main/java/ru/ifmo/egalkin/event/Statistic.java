package ru.ifmo.egalkin.event;

import java.time.LocalDate;

public class Statistic {
    private final LocalDate date;
    private final long entries;

    private Statistic(LocalDate date, long entries) {
        this.date = date;
        this.entries = entries;
    }

    public static Statistic of(LocalDate date, long entries) {
        return new Statistic(date, entries);
    }

    public LocalDate getDate() {
        return date;
    }

    public long getEntries() {
        return entries;
    }
}
