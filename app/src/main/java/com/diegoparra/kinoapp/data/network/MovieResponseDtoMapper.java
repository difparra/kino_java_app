package com.diegoparra.kinoapp.data.network;

import com.diegoparra.kinoapp.model.Movie;
import com.diegoparra.kinoapp.utils.Function;
import com.diegoparra.kinoapp.utils.ListUtils;
import com.diegoparra.kinoapp.utils.Mapper;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class MovieResponseDtoMapper implements Mapper<MovieResponseDto, List<Movie>> {

    @Inject
    public MovieResponseDtoMapper() { }

    @Override
    public List<Movie> map(MovieResponseDto movieResponseDto) {
        return ListUtils.map(movieResponseDto.getResults(), new Function<MovieDto, Movie>() {
            @Override
            public Movie apply(MovieDto movieDto) {
                return toDomainModel(movieDto);
            }
        });
    }

    private Movie toDomainModel(MovieDto movieDto) {
        String nullableTitle = (movieDto.getOriginalLanguage().equals("en")) ? movieDto.getOriginalTitle() : movieDto.getTitle();
        return new Movie(
                movieDto.getId(),
                (movieDto.getGenreIds()!=null) ? movieDto.getGenreIds() : Collections.emptyList(),
                MovieDto.IMAGE_BASE_URL + movieDto.getPosterPath(),
                (nullableTitle != null) ? nullableTitle : "No title",
                movieDto.getRating(),
                (movieDto.getReleaseDate() != null) ? LocalDate.parse(movieDto.getReleaseDate()) : LocalDate.MIN
        );
    }
}
