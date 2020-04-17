package ru.ifmo.galkin.search.generator;

import ru.ifmo.galkin.search.response.SearchItem;

public class SearchResultGenerator {

    private static int site_idx = 0;

    public static SearchItem generate(int idx, String request) {
        return new SearchItem(generateUrl(idx), generateTitle(idx, request), generateBody(idx));
    }

    private static String generateUrl(int i) {
        return String.format("https://cool_site%d/response%d", ++site_idx, i);
    }

    private static String generateTitle(int i, String request) {
        return String.format("Awesome site with %d-th most relevant result for request: %s", i, request);
    }

    private static String generateBody(int idx) {
        return String.format("Text that contains %d-th most relevant result for some request", idx);
    }
}
