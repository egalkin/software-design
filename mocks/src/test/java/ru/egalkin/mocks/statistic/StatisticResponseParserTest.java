package ru.egalkin.mocks.statistic;

import org.junit.Assert;
import org.junit.Test;

public class StatisticResponseParserTest {

    private final static String testResponse =
            "{\"response\":{\"items\":[],\"count\":1000,\"total_count\":97405,\"next_from\":\"1\\/346987506_104423\"}}";

    @Test
    public void parseResponse() throws Exception {
        StatisticResponseParser parser = new StatisticResponseParser();
        int totalCount = parser.parseResponse(testResponse);

        Assert.assertEquals(totalCount, 97405);
    }
}