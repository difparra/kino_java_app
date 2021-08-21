package com.diegoparra.kinoapp.data;

import com.diegoparra.kinoapp.model.Genre;
import com.diegoparra.kinoapp.model.Movie;
import com.diegoparra.kinoapp.model.MovieDetails;
import com.diegoparra.kinoapp.model.People;
import com.diegoparra.kinoapp.model.Review;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class FakeMoviesRepository implements MoviesRepository {

    @Override
    public Observable<List<Movie>> getMovies() {
        return Observable.just(moviesList);
    }

    @Override
    public Observable<MovieDetails> getMovieDetails(String id) {
        return Observable.just(movieDetails);
    }

    @Override
    public Observable<List<Movie>> getFavouriteMovies() {
        return Observable.just(moviesList.subList(2,7));
    }


    private static List<Movie> moviesList = new ArrayList<Movie>(
            Arrays.asList(
                    new Movie("id", Arrays.asList(""), "imageUrl", "Title", 9.0f, LocalDate.now()),
                    new Movie("id", Arrays.asList(""), "imageUrl", "Title", 9.0f, LocalDate.now()),
                    new Movie("id", Arrays.asList(""), "imageUrl", "Title", 9.0f, LocalDate.now()),
                    new Movie("id", Arrays.asList(""), "imageUrl", "Title", 9.0f, LocalDate.now()),
                    new Movie("id", Arrays.asList(""), "imageUrl", "Title", 9.0f, LocalDate.now()),
                    new Movie("id", Arrays.asList(""), "imageUrl", "Title", 9.0f, LocalDate.now()),
                    new Movie("id", Arrays.asList(""), "imageUrl", "Title", 9.0f, LocalDate.now()),
                    new Movie("id", Arrays.asList(""), "imageUrl", "Title", 9.0f, LocalDate.now()),
                    new Movie("id", Arrays.asList(""), "imageUrl", "Title", 9.0f, LocalDate.now()),
                    new Movie("id", Arrays.asList(""), "imageUrl", "Title", 9.0f, LocalDate.now())
            )
    );

    private static MovieDetails movieDetails = new MovieDetails(
            "",
            Arrays.asList(new Genre("1", "Action"), new Genre("2", "Comedy")),
            "en",
            "Suicide Squad",
            "Suicide Squad is ...",
            LocalDate.now(),
            0,
            8.0f,
            new People("0", "Director name", "Director"),
            Arrays.asList(new People("1", "Margot Robbie", "Harley Quinn"), new People("2", "Will Smith", "black")),
            Arrays.asList(moviesList.get(0), moviesList.get(1)),
            Arrays.asList(new Review("1", "Pepito", 9.0f, "Ok", LocalDateTime.now()))
    );


}
