package statistic;

import clock.Clock;
import event.Event;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventStatisticImpl implements EventStatistic {

    private Clock clock;

    private Map<String, Event> events;

    public EventStatisticImpl(Clock clock) {
        this.clock = clock;
        this.events = new HashMap<>();
    }


    public void incEvent(String name) {
        if (events.containsKey(name)) {
            events.get(name).addTimestampToStatistic(clock.now());
        } else {
            Event event = new Event(name);
            event.addTimestampToStatistic(clock.now());
            events.put(name, event);
        }
    }

    public List<Instant> getEventStatisticByName(String name) {
        if (events.containsKey(name))
            return events.get(name).getCallStatistic();
        else
            return null;
    }

    public List<Event> getAllEventStatistic() {
        return List.copyOf(events.values());
    }

    public void printStatistic() {
        for (Event event : events.values()) {
            System.out.println(event);
        }
    }
}
