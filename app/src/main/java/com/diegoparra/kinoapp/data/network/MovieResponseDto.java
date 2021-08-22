package com.diegoparra.kinoapp.data.network;

import com.diegoparra.kinoapp.model.Movie;
import com.diegoparra.kinoapp.utils.Function;
import com.diegoparra.kinoapp.utils.ListUtils;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponseDto {

    private final List<MovieDto> results;
    private final int page;
    @SerializedName("total_pages") private final int totalPages;
    @SerializedName("total_results") private final int totalResults;

    public MovieResponseDto(List<MovieDto> results, int page, int totalPages, int totalResults) {
        this.results = results;
        this.page = page;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public List<Movie> toDomainMovieList() {
        return ListUtils.map(results, (Function<MovieDto, Movie>) MovieDto::toDomainModel);
    }

}