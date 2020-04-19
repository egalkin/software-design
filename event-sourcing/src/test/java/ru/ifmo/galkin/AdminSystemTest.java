package ru.ifmo.galkin;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ifmo.egalkin.event.model.AccountCreationEvent;
import ru.ifmo.egalkin.event.model.AccountExtensionEvent;
import ru.ifmo.egalkin.system.AdminSystem;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
class AdminSystemTest extends AbstractSystemTest{

    @BeforeEach
    void setup() {
        this.adminSystem = new AdminSystem(eventStorageMock, fixedClock);
    }

    @Test
    void testAccountCreation() {
        Mockito.when(eventStorageMock.empty(any())).thenReturn(true);
        adminSystem.createAccount(1L);

        Mockito.verify(eventStorageMock, times(1))
                .save(1L, new AccountCreationEvent(LOCAL_DATE_TIME));

        Mockito.verifyNoMoreInteractions(eventStorageMock);
    }

    @Test
    void testAccountExtension() {
        Mockito.when(eventStorageMock.get(any()))
                .thenReturn(List.of(new AccountCreationEvent(LOCAL_DATE_TIME)));
        adminSystem.extendAccount(1L, LOCAL_DATE_TIME, 7);

        Mockito.verify(eventStorageMock, times(1))
                .save(1L, new AccountExtensionEvent(LOCAL_DATE_TIME, 7));

        Mockito.verifyNoMoreInteractions(eventStorageMock);
    }

    @Test
    void testCanExtendAccount() {
        Mockito.when(eventStorageMock.get(any()))
                .thenReturn(List.of(
                        new AccountCreationEvent(LOCAL_DATE_TIME),
                        new AccountExtensionEvent(LOCAL_DATE_TIME, 1))
                );

        Assertions.assertThrows(
                RuntimeException.class,
                () -> adminSystem.extendAccount(1L, LOCAL_DATE_TIME, 7)
        );
    }
}