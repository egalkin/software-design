package ru.egalkin.mocks.statistic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class StatisticCollectorIntegrationTest {

    private StatisticCollector collector;

    @Before
    public void setUp() throws Exception {
        collector = new VKStatisticCollector();
    }

    @After
    public void shutdownCollectorExecutor() throws Exception {
        collector.close();
    }

    @Test
    public void collect() {
        List<Integer> statistic = collector.collect("Еда", 15);
        Assert.assertEquals(15, statistic.size());
    }
}
