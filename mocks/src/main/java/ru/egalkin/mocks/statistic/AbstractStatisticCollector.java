package ru.egalkin.mocks.statistic;

import ru.egalkin.mocks.env.Environment;
import ru.egalkin.mocks.http.UrlReader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AbstractStatisticCollector implements StatisticCollector{
    protected final UrlReader reader = new UrlReader();
    protected final StatisticResponseParser parser = new StatisticResponseParser();
    protected final ExecutorService executor = Executors.newFixedThreadPool(Environment.DEFAULT_THREAD_NUM);

    @Override
    public void close() throws Exception {
        executor.shutdown();
    }
}
