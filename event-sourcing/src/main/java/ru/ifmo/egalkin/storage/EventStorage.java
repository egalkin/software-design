package ru.ifmo.egalkin.storage;

import org.springframework.stereotype.Component;
import ru.ifmo.egalkin.event.model.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EventStorage {
    private final Map<Long, List<Event>> events;

    public EventStorage() {
        this.events = new ConcurrentHashMap<>();
    }

    public List<Event> get(Long id) {
        return this.events.get(id);
    }

    public boolean empty(Long id) {
        return events.getOrDefault(id, Collections.emptyList()).isEmpty();
    }

    public void save(Long id, Event event) {
        if (!events.containsKey(id)) {
            events.put(id, new ArrayList<>());
        }
        events.get(id).add(event);
    }


}
