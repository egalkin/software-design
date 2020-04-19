package ru.ifmo.egalkin.event.model;

import java.time.LocalDateTime;

public class ClientEntryEvent extends AbstractTimeBasedEvent{

    public ClientEntryEvent(LocalDateTime entryTime) {
        super(entryTime);
    }

    public LocalDateTime getEntryTime() {
        return this.eventTime;
    }
 }
