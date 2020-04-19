package ru.ifmo.galkin;

import org.mockito.Mock;
import ru.ifmo.egalkin.storage.EventStorage;
import ru.ifmo.egalkin.system.AdminSystem;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class AbstractSystemTest {
    protected static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.of(2020, 3, 6, 18, 15);

    @Mock
    protected EventStorage eventStorageMock;
    protected Clock fixedClock = Clock.fixed(Instant.parse("2020-03-06T15:15:00Z"), ZoneId.systemDefault());

    protected AdminSystem adminSystem;
}
