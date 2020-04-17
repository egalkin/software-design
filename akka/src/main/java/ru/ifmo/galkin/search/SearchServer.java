package ru.ifmo.galkin.search;

import ru.ifmo.galkin.search.response.SearchResult;

public interface SearchServer {
    SearchResult search(String searchRequest);
}
