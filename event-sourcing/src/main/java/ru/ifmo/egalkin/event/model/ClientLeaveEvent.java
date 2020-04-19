package ru.ifmo.egalkin.event.model;

import java.time.LocalDateTime;

public class ClientLeaveEvent extends AbstractTimeBasedEvent{

    public ClientLeaveEvent(LocalDateTime leaveTime) {
        super(leaveTime);
    }

    public LocalDateTime getLeaveTime() {
        return this.eventTime;
    }
}
