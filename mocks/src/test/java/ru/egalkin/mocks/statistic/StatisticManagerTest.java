package ru.egalkin.mocks.statistic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class StatisticManagerTest {
    private StatisticManager statisticManager;

    @Mock
    private StatisticCollector collector;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.statisticManager = new StatisticManager(collector);
    }

    @After
    public void shutdownCollectorExecutor() throws Exception {
        collector.close();
    }

    @Test
    public void getHashtagUsageStatistic() {
        String hashtag = "Еда";
        int hours = 5;
        when(collector.collect(hashtag, hours))
                .thenReturn(createAnswer());
        List<Integer> statistic = statisticManager.getHashtagUsageStatistic(hashtag, hours);
        Assert.assertEquals(Arrays.asList(45,12,34,22,5), statistic);
    }

    private List<Integer> createAnswer() {
        return Arrays.asList(45,12,34,22,5);
    }

}
