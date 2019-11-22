package com.jsystems.qa.qaapi.model.azure.book;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {

    @JsonProperty(value = "ID", required = true)
    public long id;

    @JsonProperty(value = "Title", required = true)
    public String title;

    @JsonProperty(value = "Description", required = true)
    public String description;

    @JsonProperty(value = "PageCount", required = true)
    public int pageCount;

    @JsonProperty(value = "Exerpt", required = true)
    public String exerpt;

    @JsonProperty(value = "PublishDate", required = true)
    public String publishDate;

    public Book(long id, String title, String description, int pageCount, String exerpt, String publishDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pageCount = pageCount;
        this.exerpt = exerpt;
        this.publishDate = publishDate;
    }

    public Book() {
    }
}
