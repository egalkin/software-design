package ru.ifmo.galkin.search.response;

public class SearchItem {

    private String url;
    private String title;
    private String body;

    public SearchItem(String url, String title, String body) {
        this.url = url;
        this.title = title;
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }


    @Override
    public String toString() {
        return "SearchResult{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
