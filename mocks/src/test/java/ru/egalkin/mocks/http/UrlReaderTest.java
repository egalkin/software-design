package ru.egalkin.mocks.http;

import org.junit.Assert;
import org.junit.Test;
import ru.egalkin.mocks.env.Environment;
import ru.egalkin.mocks.http.UrlReader;

public class UrlReaderTest {

    @Test
    public void read() {
        String result = new UrlReader()
                .readAsText(String.format(Environment.HASHTAG_STATISTIC_TEMPLATE
                        , "%23еда"
                        , 0
                        , 0
                        , Environment.DEFAULT_ACCESS_TOKEN));
        Assert.assertTrue(result.length() > 0);
    }
}
