package com.diegoparra.kinoapp.data;

import com.diegoparra.kinoapp.data.network.MovieDetailsDto;
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
    private final Mapper<MovieResponseDto, List<Movie>> moviesMapper;
    private final Mapper<MovieDetailsDto, MovieDetails> movieDetailsMapper;

    @Inject
    public MoviesRepositoryImpl(MoviesService moviesService, Mapper<MovieResponseDto, List<Movie>> moviesMapper, Mapper<MovieDetailsDto, MovieDetails> movieDetailsMapper) {
        this.moviesService = moviesService;
        this.moviesMapper = moviesMapper;
        this.movieDetailsMapper = movieDetailsMapper;
    }

    @Override
    public Observable<List<Movie>> getMovies() {
        return moviesService.getMovies().map(new Function<MovieResponseDto, List<Movie>>() {
            @Override
            public List<Movie> apply(MovieResponseDto movieResponseDto) throws Throwable {
                return moviesMapper.map(movieResponseDto);
            }
        });
    }

    @Override
    public Observable<MovieDetails> getMovieDetails(String id) {
        return moviesService.getMovieDetails(id).map(new Function<MovieDetailsDto, MovieDetails>() {
            @Override
            public MovieDetails apply(MovieDetailsDto movieDetailsDto) throws Throwable {
                return movieDetailsMapper.map(movieDetailsDto);
            }
        });
    }

    @Override
    public Observable<List<Movie>> getFavouriteMovies() {
        return Observable.just(FakeMoviesRepository.moviesList.subList(2, 7));
    }
}
