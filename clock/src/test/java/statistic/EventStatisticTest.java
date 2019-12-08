package statistic;

import clock.SetableClock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

import java.time.Instant;

public class EventStatisticTest  {

    private Instant now;
    private SetableClock clock;
    private EventStatistic eventStatistic;

    @BeforeEach
    public void setUp() throws Exception {
        now = Instant.now();
        clock = new SetableClock(now);
        eventStatistic = new EventStatisticImpl(clock);
    }

    @Test
    public void singleEventStatisticTest() {
        for (int i = 1; i <= 50; ++i) {
            eventStatistic.incEvent("SingleEvent");
            now = now.plusSeconds(60);
            clock.setNow(now);
        }
        Assert.assertEquals(50, eventStatistic.getEventStatisticByName("SingleEvent").size());
    }

    @Test
    public void eventInPastTest() {
        now = now.minusSeconds(3600);
        clock.setNow(now);
        for (int i = 1; i <= 20; ++i) {
            now = now.minusSeconds(60);
            clock.setNow(now);
            eventStatistic.incEvent("PastEvent");
        }
        Assert.assertEquals(0, eventStatistic.getEventStatisticByName("PastEvent").size());
    }

    @Test
    public void filterOutOldDataTest() {
        for (int i = 1; i <= 120; ++i) {
            eventStatistic.incEvent("FilterEvent");
            now = now.minusSeconds(60);
            clock.setNow(now);
        }
        Assert.assertEquals(60, eventStatistic.getEventStatisticByName("FilterEvent").size());
    }
}
