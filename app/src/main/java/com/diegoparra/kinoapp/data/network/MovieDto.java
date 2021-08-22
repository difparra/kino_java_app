package com.diegoparra.kinoapp.data.network;

import com.diegoparra.kinoapp.model.Movie;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.List;

public class MovieDto {

    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original";

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


    public Movie toDomainModel() {
        String title;
        if (originalLanguage.equals("en")) {
            title = originalTitle;
        } else {
            title = this.title;
        }
        String imageUrl = IMAGE_BASE_URL + posterPath;
        return new Movie(
                id,
                genreIds,
                imageUrl,
                title,
                rating,
                LocalDate.parse(releaseDate)
        );
    }
}