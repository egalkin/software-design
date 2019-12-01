package ru.egalkin.mocks.statistic;

import ru.egalkin.mocks.env.Environment;
import ru.egalkin.mocks.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class VKStatisticCollector extends AbstractStatisticCollector {

    @Override
    public List<Integer> collect(String hashtag, int hoursNumber) {
        List<Future<Integer>> statistic = new ArrayList<>(hoursNumber);
        long startTime = System.currentTimeMillis() / 1000 - hoursNumber * 3600;
        for (int hrMultiplier = 0; hrMultiplier < hoursNumber; ++hrMultiplier) {
            final int multiplier = hrMultiplier;
            statistic.add(multiplier, executor.submit(() -> parser.parseResponse(reader.readAsText(createUrl(hashtag, startTime, multiplier)))));
        }
        return statistic.stream().map(fut -> {
            try {
                return fut.get();
            } catch (Exception ex) {
                return -1;
            }
        }).collect(Collectors.toList());
    }

    private String createUrl(String hashtag, long startTime, int hrMultiplier) {
        return String.format(Environment.HASHTAG_STATISTIC_TEMPLATE
                , "%23" + hashtag
                , startTime + hrMultiplier * TimeUtils.secondsInHour
                , startTime + (hrMultiplier + 1) * TimeUtils.secondsInHour
                , Environment.DEFAULT_ACCESS_TOKEN);
    }
}
