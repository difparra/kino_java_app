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

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class FakeMoviesRepository implements MoviesRepository {

    @Inject
    public FakeMoviesRepository() {
    }

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
        return Observable.just(moviesList.subList(2, 7));
    }

    @Override
    public Observable<Boolean> isFavouriteMovie(String id) {
        //  TODO
        return Observable.just(false);
    }

    @Override
    public void addFavouriteMovie(Movie movie) {
        //  TODO
    }

    @Override
    public void deleteFavouriteMovie(String id) {
        //  TODO
    }

    static List<Movie> moviesList = new ArrayList<Movie>(
            Arrays.asList(
                    new Movie("436969", Arrays.asList("28", "12", "14"), "https://image.tmdb.org/t/p/original/iCi4c4FvVdbaU1t8poH1gvzT6xM.jpg", "The Suicide Squad", 8.1f, LocalDate.of(2021, 7, 28)),
                    new Movie("451048", Arrays.asList("12", "14", "35", "28"), "https://image.tmdb.org/t/p/original/9dKCd55IuTT5QRs989m9Qlb7d2B.jpg", "Jungle Cruise", 7.9f, LocalDate.of(2021, 7, 28)),
                    new Movie("550988", Arrays.asList("35", "28", "12", "878"), "https://image.tmdb.org/t/p/original/acCS12FVUQ7blkC8qEbuXbsWEs2.jpg", "Free Guy", 7.9f, LocalDate.of(2021, 8, 11)),
                    new Movie("497698", Arrays.asList("28", "12", "53", "878"), "https://image.tmdb.org/t/p/original/qAZ0pzat24kLdO3o8ejmbLxyOac.jpg", "Black Widow", 7.8f, LocalDate.of(2021, 7, 7)),
                    new Movie("379686", Arrays.asList("16", "35", "10751", "878"), "https://image.tmdb.org/t/p/original/5bFK5d3mVTAvBCXi5NPWH0tYjKl.jpg", "Space Jam: A New Legacy", 7.5f, LocalDate.of(2021, 7, 8)),
                    new Movie("581726", Arrays.asList("878", "28", "53"), "https://image.tmdb.org/t/p/original/niw2AKHz6XmwiRMLWaoyAOAti0G.jpg", "Infinite", 7.4f, LocalDate.of(2021, 6, 10)),
                    new Movie("385128", Arrays.asList("28", "80", "53"), "https://image.tmdb.org/t/p/original/bOFaAXmWWXC3Rbv4u4uM9ZSzRXP.jpg", "F9", 7.6f, LocalDate.of(2021, 5, 19)),
                    new Movie("729720", Arrays.asList("28", "35"), "https://image.tmdb.org/t/p/original/ttpKJ7XQxDZV252KNEHXtykYT41.jpg", "The Last Mercenary", 7.1f, LocalDate.of(2021, 7, 30)),
                    new Movie("459151", Arrays.asList("16", "35", "12", "10751"), "https://image.tmdb.org/t/p/original/kv2Qk9MKFFQo4WQPaYta599HkJP.jpg", "The Boss Baby: Family Business", 7.8f, LocalDate.of(2021, 7, 1)),
                    new Movie("508943", Arrays.asList("16", "35", "10751", "14"), "https://image.tmdb.org/t/p/original/jTswp6KyDYKtvC52GbHagrZbGvD.jpg", "Luca", 8.1f, LocalDate.of(2021, 6, 17))
            )
    );


    static MovieDetails movieDetails = new MovieDetails(
            "436969",
            Arrays.asList(new Genre("28", "Action"), new Genre("12", "Adventure"), new Genre("14", "Fantasy")),
            "en",
            "The Suicide Squad",
            "https://image.tmdb.org/t/p/original/iCi4c4FvVdbaU1t8poH1gvzT6xM.jpg",
            "https://image.tmdb.org/t/p/original/jlGmlFOcfo8n5tURmhC7YVd4Iyy.jpg",
            "Supervillains Harley Quinn, Bloodsport, Peacemaker and a collection of nutty cons at Belle Reve prison join the super-secret, super-shady Task Force X as they are dropped off at the remote, enemy-infused island of Corto Maltese.",
            LocalDate.of(2021, 7, 28),
            132,
            8.1f,
            new People("0", "Director name", "Director"),
            Arrays.asList(new People("1", "Margot Robbie", "Harley Quinn"), new People("2", "Will Smith", "black")),
            Arrays.asList(moviesList.get(0), moviesList.get(1)),
            Arrays.asList(new Review("1", "Pepito", 9.0f, "Ok", LocalDateTime.now()))
    );


}
