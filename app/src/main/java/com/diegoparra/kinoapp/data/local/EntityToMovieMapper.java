package com.diegoparra.kinoapp.data.local;

import com.diegoparra.kinoapp.model.Movie;
import com.diegoparra.kinoapp.utils.Mapper;

import javax.inject.Inject;

public class EntityToMovieMapper implements Mapper<MovieEntity, Movie> {

    @Inject
    public EntityToMovieMapper() { }

    @Override
    public Movie map(MovieEntity movieEntity) {
        return new Movie(
                movieEntity.getId(),
                movieEntity.getGenreIds(),
                movieEntity.getPosterUrl(),
                movieEntity.getTitle(),
                movieEntity.getRating(),
                movieEntity.getReleaseDate()
        );
    }
}
