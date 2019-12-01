package ru.egalkin.mocks.statistic;

import java.util.List;
import java.util.stream.Collectors;

public class StatisticManager {
    private final StatisticCollector collector;

    public StatisticManager(StatisticCollector collector) {
        this.collector = collector;
    }

    public List<Integer> getHashtagUsageStatistic(String hashtag, int hours) {
        return collector.collect(hashtag, hours)
                .stream()
                .filter(val -> val >= 0)
                .collect(Collectors.toList());
    }

}
