package ru.egalkin.mocks.statistic;

import java.util.List;

public interface StatisticCollector extends AutoCloseable {

    List<Integer> collect(String hashtag, int hoursNumber);
}
