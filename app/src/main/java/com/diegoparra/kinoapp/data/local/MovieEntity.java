package com.diegoparra.kinoapp.data.local;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.util.List;

@Entity(tableName = "MovieEntity")
public class MovieEntity {

    @NonNull
    @PrimaryKey
    private String id;
    private List<String> genreIds;
    private String posterUrl;
    private String title;
    private float rating;
    private LocalDate releaseDate;


    public MovieEntity(String id, List<String> genreIds, String posterUrl, String title, float rating, LocalDate releaseDate) {
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

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<String> genreIds) {
        this.genreIds = genreIds;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
