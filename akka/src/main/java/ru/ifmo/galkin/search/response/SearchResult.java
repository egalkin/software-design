package ru.ifmo.galkin.search.response;

import ru.ifmo.galkin.search.SearchEngine;

import java.util.List;

public class SearchResult {


    private List<SearchItem> searchResult;
    private SearchEngine searchEngine;

    public SearchResult(List<SearchItem> searchResult, SearchEngine searchEngine) {
        this.searchResult = searchResult;
        this.searchEngine = searchEngine;
    }

    public List<SearchItem> getSearchResult() {
        return searchResult;
    }

    public SearchEngine getSearchEngine() {
        return searchEngine;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "searchResult=" + searchResult +
                ", searchEngine=" + searchEngine +
                '}';
    }
}
