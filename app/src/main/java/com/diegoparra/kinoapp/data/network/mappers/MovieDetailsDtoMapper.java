package com.diegoparra.kinoapp.data.network.mappers;

import com.diegoparra.kinoapp.data.network.GenreDto;
import com.diegoparra.kinoapp.data.network.MovieDetailsDto;
import com.diegoparra.kinoapp.model.Genre;
import com.diegoparra.kinoapp.model.MovieDetails;
import com.diegoparra.kinoapp.model.People;
import com.diegoparra.kinoapp.utils.ListUtils;
import com.diegoparra.kinoapp.utils.Mapper;

import java.time.LocalDate;
import java.util.Collections;

import javax.inject.Inject;

public class MovieDetailsDtoMapper implements Mapper<MovieDetailsDto, MovieDetails> {

    private final Mapper<GenreDto, Genre> genreMapper;

    @Inject
    public MovieDetailsDtoMapper(Mapper<GenreDto, Genre> genreMapper) {
        this.genreMapper = genreMapper;
    }

    @Override
    public MovieDetails map(MovieDetailsDto movieDetailsDto) {
        String nullableTitle = (movieDetailsDto.getLanguage().equals("en")) ? movieDetailsDto.getOriginalTitle() : movieDetailsDto.getTitle();
        return new MovieDetails(
                movieDetailsDto.getId(),
                (movieDetailsDto.getGenres() != null) ? ListUtils.map(movieDetailsDto.getGenres(), genreMapper::map) : Collections.emptyList(),
                (movieDetailsDto.getLanguage() != null) ? movieDetailsDto.getLanguage() : "en",
                (nullableTitle != null) ? nullableTitle : "No title",
                MovieDetailsDto.IMAGE_BASE_URL + movieDetailsDto.getBackdropPath(),
                (movieDetailsDto.getOverview() != null) ? movieDetailsDto.getOverview() : "No overview,",
                (movieDetailsDto.getReleaseDate() != null) ? LocalDate.parse(movieDetailsDto.getReleaseDate()) : LocalDate.MIN,
                movieDetailsDto.getRuntime(),
                movieDetailsDto.getRating(),
                new People("", "", ""),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }
}
