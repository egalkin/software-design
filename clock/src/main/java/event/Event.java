package event;

import statistic.EventStatistic;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Event {
    private String name;
    private List<Instant> statistic;

    public Event(String name) {
        this.name = name;
        this.statistic = new ArrayList<>();
    }

    public void addTimestampToStatistic(Instant timestamp) {
//        System.out.println(statistic.size());
        statistic.add(timestamp);
    }

    public List<Instant> getCallStatistic() {
        filterOutOutdatedRecords();
        return this.statistic;
    }

    private void filterOutOutdatedRecords() {
        long filterBorder = System.currentTimeMillis() / 1000 - 3600;
        this.statistic = this.statistic.stream().filter(record -> record.getEpochSecond() > filterBorder).collect(Collectors.toList());

    }

    @Override
    public String toString() {
        filterOutOutdatedRecords();
        StringJoiner view = new StringJoiner("");
        view.add("Name: \"" + name + "\"\n");
        view.add("Num of events: " + statistic.size() + "\n");
        statistic.forEach(record -> view.add(record.toString() + ", "));
        view.add("\n||||||||||||||||||||||||||||||");
        return view.toString();
    }
}
