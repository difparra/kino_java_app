package com.diegoparra.kinoapp.data;

import com.diegoparra.kinoapp.model.Movie;
import com.diegoparra.kinoapp.model.MovieDetails;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface MoviesRepository {

    Observable<List<Movie>> getMovies();
    Observable<MovieDetails> getMovieDetails(String id);
    Observable<List<Movie>> getFavouriteMovies();
    Observable<Boolean> isFavouriteMovie(String id);
    void addFavouriteMovie(Movie movie);
    void deleteFavouriteMovie(String id);

}
