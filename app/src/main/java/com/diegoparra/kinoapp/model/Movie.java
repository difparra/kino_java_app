package com.diegoparra.kinoapp.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Movie {

    private final String id;
    private final List<String> genreIds;
    private final String posterUrl;
    private final String title;
    private final float rating;
    private final LocalDate releaseDate;


    public Movie(String id, List<String> genreIds, String posterUrl, String title, Float rating, LocalDate releaseDate) {
        this.id = id;
        this.genreIds = genreIds;
        this.posterUrl = posterUrl;
        this.title = title;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }


    public String getId() {
        return id;
    }

    public List<String> getGenreIds() {
        return genreIds;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getTitle() {
        return title;
    }

    public Float getRating() {
        return rating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Float.compare(movie.rating, rating) == 0 && id.equals(movie.id) && genreIds.equals(movie.genreIds) && posterUrl.equals(movie.posterUrl) && title.equals(movie.title) && releaseDate.equals(movie.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, genreIds, posterUrl, title, rating, releaseDate);
    }
}
