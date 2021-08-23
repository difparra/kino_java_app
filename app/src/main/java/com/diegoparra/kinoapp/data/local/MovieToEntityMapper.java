package com.diegoparra.kinoapp.data.local;

import com.diegoparra.kinoapp.model.Movie;
import com.diegoparra.kinoapp.utils.Mapper;

import javax.inject.Inject;

public class MovieToEntityMapper implements Mapper<Movie, MovieEntity> {

    @Inject
    public MovieToEntityMapper() {}

    @Override
    public MovieEntity map(Movie movie) {
        return new MovieEntity(
                movie.getId(),
                movie.getGenreIds(),
                movie.getPosterUrl(),
                movie.getTitle(),
                movie.getRating(),
                movie.getReleaseDate()
        );
    }
}
