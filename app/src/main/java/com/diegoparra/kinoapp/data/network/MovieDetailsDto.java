package com.diegoparra.kinoapp.data.network;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.List;

public class MovieDetailsDto {

    public static final String IMAGE_BASE_URL = MovieDto.IMAGE_BASE_URL;

    private final String id;

    @SerializedName("genres")
    private final List<GenreDto> genres;
    @SerializedName("original_title")
    private final String originalTitle;
    @SerializedName("title")
    private final String title;
    @SerializedName("original_language")
    private final String language;
    @SerializedName("poster_path")
    private final String posterPath;
    @SerializedName("backdrop_path")
    private final String backdropPath;
    @SerializedName("overview")
    private final String overview;
    @SerializedName("release_date")
    private final String releaseDate;
    @SerializedName("runtime")
    private final int runtime;
    @SerializedName("vote_average")
    private final float rating;

    public MovieDetailsDto(String id, List<GenreDto> genres, String originalTitle, String title, String language, String posterPath, String backdropPath, String overview, String releaseDate, int runtime, float rating) {
        this.id = id;
        this.genres = genres;
        this.originalTitle = originalTitle;
        this.title = title;
        this.language = language;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public List<GenreDto> getGenres() {
        return genres;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return language;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getRuntime() {
        return runtime;
    }

    public float getRating() {
        return rating;
    }
}