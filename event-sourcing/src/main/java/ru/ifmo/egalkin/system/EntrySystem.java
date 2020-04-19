package ru.ifmo.egalkin.system;

import org.springframework.stereotype.Component;
import ru.ifmo.egalkin.event.EventCaster;
import ru.ifmo.egalkin.event.model.AccountExtensionEvent;
import ru.ifmo.egalkin.event.model.ClientEntryEvent;
import ru.ifmo.egalkin.event.model.ClientLeaveEvent;
import ru.ifmo.egalkin.exception.EnterRejectedException;
import ru.ifmo.egalkin.storage.EventStorage;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Comparator;

@Component
public class EntrySystem {
    private final EventStorage eventStorage;
    private final Clock clock;

    public EntrySystem(EventStorage eventStorage, Clock clock) {
        this.eventStorage = eventStorage;
        this.clock = clock;
    }

    public void enter(long id) {
        final var now = LocalDateTime.now(clock);
        if (!canEnter(id, now)) {
            throw new EnterRejectedException();
        }

        eventStorage.save(id, new ClientEntryEvent(now));
    }

    public void leave(long id) {
        eventStorage.save(id, new ClientLeaveEvent(LocalDateTime.now(clock)));
    }

    private boolean canEnter(long id, LocalDateTime now) {
        final var expireDateTime = eventStorage.get(id)
                .stream()
                .filter(e -> EventCaster.is(e, AccountExtensionEvent.class))
                .map(e -> EventCaster.cast(e, AccountExtensionEvent.class))
                .max(Comparator.comparing(AccountExtensionEvent::getExtensionTime))
                .map(e -> e.getExtensionTime().plusDays(e.getDays()))
                .map(date -> date.plusDays(1))
                .orElse(LocalDateTime.MIN);

        return now.isBefore(expireDateTime);
    }
}
