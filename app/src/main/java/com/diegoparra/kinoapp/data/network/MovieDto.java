package com.diegoparra.kinoapp.data.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDto {

    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original";

    private final String id;
    @SerializedName("genre_ids")
    private final List<String> genreIds;
    @SerializedName("original_title")
    private final String originalTitle;
    @SerializedName("title")
    private final String title;
    @SerializedName("poster_path")
    private final String posterPath;
    @SerializedName("original_language")
    private final String originalLanguage;
    @SerializedName("release_date")
    private final String releaseDate;
    @SerializedName("vote_average")
    private final float rating;

    public MovieDto(String id, List<String> genreIds, String originalTitle, String title, String posterPath, String originalLanguage, String releaseDate, float rating) {
        this.id = id;
        this.genreIds = genreIds;
        this.originalTitle = originalTitle;
        this.title = title;
        this.posterPath = posterPath;
        this.originalLanguage = originalLanguage;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public List<String> getGenreIds() {
        return genreIds;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public float getRating() {
        return rating;
    }
}