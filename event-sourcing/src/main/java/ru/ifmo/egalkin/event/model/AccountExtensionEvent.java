package ru.ifmo.egalkin.event.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class AccountExtensionEvent extends AbstractTimeBasedEvent {

    private final int days;

    public AccountExtensionEvent(LocalDateTime extensionTime, int days) {
        super(extensionTime);
        this.days = days;
    }

    public LocalDateTime getExtensionTime() {
        return this.eventTime;
    }

    public int getDays() {
        return days;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountExtensionEvent)) {
            return false;
        }
        AccountExtensionEvent that = (AccountExtensionEvent) o;
        return days == that.days &&
                eventTime.equals(that.eventTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventTime, days);
    }
}

