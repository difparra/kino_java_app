package com.diegoparra.kinoapp.data.network.mappers;

import com.diegoparra.kinoapp.data.network.MovieDto;
import com.diegoparra.kinoapp.model.Movie;
import com.diegoparra.kinoapp.utils.Mapper;

import java.time.LocalDate;
import java.util.Collections;

import javax.inject.Inject;

public class MovieDtoMapper implements Mapper<MovieDto, Movie> {

    @Inject
    public MovieDtoMapper() { }

    @Override
    public Movie map(MovieDto movieDto) {
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
