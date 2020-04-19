package ru.ifmo.egalkin.event;

import ru.ifmo.egalkin.event.model.Event;

import java.util.ArrayList;
import java.util.List;

public class EventHandler {

    private final List<EventListener> listeners;

    public EventHandler() {
        this.listeners = new ArrayList<>();
    }

    public void addListener(EventListener listener) {
        this.listeners.add(listener);
    }

    public void hanlde(long id, Event event) {
        listeners.forEach(listener -> listener.handle(id, event));
    }
}
