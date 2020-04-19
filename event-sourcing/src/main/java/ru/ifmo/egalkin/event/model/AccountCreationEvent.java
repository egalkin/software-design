package ru.ifmo.egalkin.event.model;

import java.time.LocalDateTime;

public class AccountCreationEvent extends AbstractTimeBasedEvent {
    public AccountCreationEvent(LocalDateTime creationTime) {
        super(creationTime);
    }

    public LocalDateTime getCreationTime() {
        return this.eventTime;
    }

}
