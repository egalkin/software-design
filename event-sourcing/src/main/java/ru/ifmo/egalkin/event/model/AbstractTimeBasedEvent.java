package ru.ifmo.egalkin.event.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class AbstractTimeBasedEvent implements Event {

    protected final LocalDateTime eventTime;

    public AbstractTimeBasedEvent(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractTimeBasedEvent)) {
            return false;
        }
        AbstractTimeBasedEvent that = (AbstractTimeBasedEvent) o;
        return Objects.equals(eventTime, that.eventTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventTime);
    }
}
