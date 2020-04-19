package ru.ifmo.egalkin.event;

import ru.ifmo.egalkin.event.model.Event;

public interface EventListener {
    void handle(long accountId, Event event);
}
