package ru.ifmo.egalkin.event;

import ru.ifmo.egalkin.event.model.Event;

public class EventCaster {

    private EventCaster() {
        throw new UnsupportedOperationException();
    }

    public static <E extends Event> E cast(Event e, Class<E> eventType) {
        if (is(e, eventType)) {
            return (E) e;
        }
        return null;
    }

    public static <E extends Event> boolean is(Event e, Class<E> eventType) {
        return eventType.isInstance(e);
    }
}
