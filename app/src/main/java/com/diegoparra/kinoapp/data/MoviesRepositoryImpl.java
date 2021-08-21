package com.diegoparra.kinoapp.data;

import com.diegoparra.kinoapp.model.Movie;
import com.diegoparra.kinoapp.model.MovieDetails;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class MoviesRepositoryImpl implements MoviesRepository {

    @Inject
    public MoviesRepositoryImpl() {}

    @Override
    public Observable<List<Movie>> getMovies() {
        return null;
    }

    @Override
    public Observable<MovieDetails> getMovieDetails(String id) {
        return null;
    }

    @Override
    public Observable<List<Movie>> getFavouriteMovies() {
        return null;
    }
}
