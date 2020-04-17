package ru.ifmo.galkin.search;

import ru.ifmo.galkin.search.generator.SearchResultGenerator;
import ru.ifmo.galkin.search.response.SearchItem;
import ru.ifmo.galkin.search.response.SearchResult;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SearchServerStub implements SearchServer {

    private static final int SELECTION_SIZE = 5;

    private final SearchEngine searchEngine;

    public SearchServerStub(SearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    @Override
    public SearchResult search(String searchRequest) {
        List<SearchItem> response = IntStream.range(0, SELECTION_SIZE)
                .mapToObj(i -> SearchResultGenerator.generate(i, searchRequest))
                .collect(Collectors.toList());

        return new SearchResult(response, searchEngine);
    }
}
