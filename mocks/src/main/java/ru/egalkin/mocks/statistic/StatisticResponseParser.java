package ru.egalkin.mocks.statistic;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.egalkin.mocks.http.UrlReader;

public class StatisticResponseParser {
    public static void main(String... args) {
        System.out.println(new StatisticResponseParser().parseResponse(new UrlReader().readAsText(args[0])));
    }

    public int parseResponse(String response) {
        JsonObject parsedResponse = (JsonObject) new JsonParser().parse(response).getAsJsonObject().get("response");
        return parsedResponse.get("total_count").getAsInt();
    }
}
