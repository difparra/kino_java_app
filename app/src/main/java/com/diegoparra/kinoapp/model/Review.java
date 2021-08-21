package com.diegoparra.kinoapp.model;

import java.time.LocalDateTime;

public class Review {

    private final String id;
    private final String author;
    private final Float rating;
    private final String content;
    private final LocalDateTime updatedAt;


    public Review(String id, String author, Float rating, String content, LocalDateTime updatedAt) {
        this.id = id;
        this.author = author;
        this.rating = rating;
        this.content = content;
        this.updatedAt = updatedAt;
    }


    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public Float getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
