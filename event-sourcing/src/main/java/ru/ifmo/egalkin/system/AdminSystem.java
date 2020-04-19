package ru.ifmo.egalkin.system;

import org.springframework.stereotype.Component;
import ru.ifmo.egalkin.event.EventCaster;
import ru.ifmo.egalkin.event.model.AccountCreationEvent;
import ru.ifmo.egalkin.event.model.AccountExtensionEvent;
import ru.ifmo.egalkin.event.model.Event;
import ru.ifmo.egalkin.exception.AccountExpiringException;
import ru.ifmo.egalkin.exception.AccountUniqunessException;
import ru.ifmo.egalkin.exception.NoSuchAccountException;
import ru.ifmo.egalkin.storage.EventStorage;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Comparator;

@Component
public class AdminSystem {
    private final EventStorage eventStorage;
    private final Clock clock;

    public AdminSystem(EventStorage eventStorage, Clock clock) {
        this.eventStorage = eventStorage;
        this.clock = clock;
    }

    public void createAccount(long id) {
        if (!eventStorage.empty(id)) {
            throw new AccountUniqunessException();
        }

        Event event = new AccountCreationEvent(LocalDateTime.now(clock));
        eventStorage.save(id, event);
    }

    public void extendAccount(long id, LocalDateTime extensionDate, int days){
        final boolean isCreated = eventStorage.get(id)
                .stream()
                .findFirst()
                .map(e -> EventCaster.is(e, AccountCreationEvent.class))
                .orElse(false);

        if (!isCreated) {
            throw new NoSuchAccountException();
        }

        validateExtension(id, extensionDate);

        final var event = new AccountExtensionEvent(extensionDate, days);
        eventStorage.save(id, event);
    }

    private void validateExtension(long id, LocalDateTime extensionDate) {
        final LocalDateTime expireDate = eventStorage.get(id)
                .stream()
                .filter(e -> EventCaster.is(e, AccountExtensionEvent.class))
                .map(e -> EventCaster.cast(e, AccountExtensionEvent.class))
                .max(Comparator.comparing(AccountExtensionEvent::getExtensionTime))
                .map(e -> e.getExtensionTime().plusDays(e.getDays()))
                .orElse(LocalDateTime.MIN);

        if (expireDate.isAfter(extensionDate)) {
            throw new AccountExpiringException();
        }
    }
}
