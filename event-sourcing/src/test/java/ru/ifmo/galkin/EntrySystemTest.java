package ru.ifmo.galkin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ifmo.egalkin.event.model.AccountExtensionEvent;
import ru.ifmo.egalkin.event.model.ClientEntryEvent;
import ru.ifmo.egalkin.event.model.ClientLeaveEvent;;
import ru.ifmo.egalkin.system.EntrySystem;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class EntrySystemTest extends AbstractSystemTest {


    private EntrySystem entrySystem;

    @BeforeEach
    void setup() {
        this.entrySystem = new EntrySystem(eventStorageMock, fixedClock);
    }

    @Test
    void testEnter() {
        Mockito.when(eventStorageMock.get(eq(1L)))
                .thenReturn(List.of(new AccountExtensionEvent(LOCAL_DATE_TIME, 7)));

        entrySystem.enter(1L);
        Mockito.verify(eventStorageMock, times(1))
                .save(1L, new ClientEntryEvent(LOCAL_DATE_TIME));
    }

    @Test
    void testLeave() {
        entrySystem.leave(1L);
        Mockito.verify(eventStorageMock, times(1))
                .save(1L, new ClientLeaveEvent(LOCAL_DATE_TIME));
    }

    @Test
    void testCantEnter() {
        Mockito.when(eventStorageMock.get(eq(1L)))
                .thenReturn(List.of(new AccountExtensionEvent(LOCAL_DATE_TIME.minusDays(10), 1)));

        Assertions.assertThrows(
                RuntimeException.class,
                () -> entrySystem.enter(1L)
        );
    }
}
