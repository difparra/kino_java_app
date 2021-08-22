package com.diegoparra.kinoapp.data;

import com.diegoparra.kinoapp.data.network.MovieResponseDto;
import com.diegoparra.kinoapp.data.network.MoviesService;
import com.diegoparra.kinoapp.model.Movie;
import com.diegoparra.kinoapp.model.MovieDetails;
import com.diegoparra.kinoapp.utils.Mapper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;

public class MoviesRepositoryImpl implements MoviesRepository {

    private final MoviesService moviesService;
    private final Mapper<MovieResponseDto, List<Movie>> mapper;

    @Inject
    public MoviesRepositoryImpl(MoviesService moviesService, Mapper<MovieResponseDto, List<Movie>> mapper) {
        this.moviesService = moviesService;
        this.mapper = mapper;
    }

    @Override
    public Observable<List<Movie>> getMovies() {
        return moviesService.getMovies().map(new Function<MovieResponseDto, List<Movie>>() {
            @Override
            public List<Movie> apply(MovieResponseDto movieResponseDto) throws Throwable {
                return mapper.map(movieResponseDto);
            }
        });
    }

    @Override
    public Observable<MovieDetails> getMovieDetails(String id) {
        return Observable.just(FakeMoviesRepository.movieDetails);
    }

    @Override
    public Observable<List<Movie>> getFavouriteMovies() {
        return Observable.just(FakeMoviesRepository.moviesList.subList(2, 7));
    }
}
