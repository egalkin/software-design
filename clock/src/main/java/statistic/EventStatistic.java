package statistic;

import event.Event;

import java.time.Instant;
import java.util.List;

public interface EventStatistic {
    void incEvent(String name);
    List<Instant> getEventStatisticByName(String name);
    List<Event> getAllEventStatistic();
    void printStatistic();
}
