package com.diegoparra.kinoapp.data;

import android.util.Log;

import com.diegoparra.kinoapp.data.local.MovieEntity;
import com.diegoparra.kinoapp.data.local.MoviesDao;
import com.diegoparra.kinoapp.data.network.MovieDetailsDto;
import com.diegoparra.kinoapp.data.network.MovieResponseDto;
import com.diegoparra.kinoapp.data.network.MoviesService;
import com.diegoparra.kinoapp.model.Movie;
import com.diegoparra.kinoapp.model.MovieDetails;
import com.diegoparra.kinoapp.utils.ListUtils;
import com.diegoparra.kinoapp.utils.Mapper;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MoviesRepositoryImpl implements MoviesRepository {

    private static final String TAG = "MoviesRepositoryImpl";

    private final MoviesService moviesService;
    private final Mapper<MovieResponseDto, List<Movie>> dtoToMovieMapper;
    private final Mapper<MovieDetailsDto, MovieDetails> dtoToMovieDetailsMapper;
    private final MoviesDao moviesDao;
    private final Mapper<MovieEntity, Movie> entityToMovieMapper;
    private final Mapper<Movie, MovieEntity> movieToEntityMapper;


    @Inject
    public MoviesRepositoryImpl(MoviesService moviesService, Mapper<MovieResponseDto, List<Movie>> dtoToMovieMapper, Mapper<MovieDetailsDto, MovieDetails> dtoToMovieDetailsMapper,
                                MoviesDao moviesDao, Mapper<MovieEntity, Movie> entityToMovieMapper, Mapper<Movie, MovieEntity> movieToEntityMapper) {
        this.moviesService = moviesService;
        this.dtoToMovieMapper = dtoToMovieMapper;
        this.dtoToMovieDetailsMapper = dtoToMovieDetailsMapper;
        this.moviesDao = moviesDao;
        this.entityToMovieMapper = entityToMovieMapper;
        this.movieToEntityMapper = movieToEntityMapper;
    }

    @Override
    public Observable<List<Movie>> getMovies() {
        return moviesService.getMovies().map(new Function<MovieResponseDto, List<Movie>>() {
            @Override
            public List<Movie> apply(MovieResponseDto movieResponseDto) throws Throwable {
                return dtoToMovieMapper.map(movieResponseDto);
            }
        });
    }

    @Override
    public Observable<MovieDetails> getMovieDetails(String id) {
        return moviesService.getMovieDetails(id).map(new Function<MovieDetailsDto, MovieDetails>() {
            @Override
            public MovieDetails apply(MovieDetailsDto movieDetailsDto) throws Throwable {
                return dtoToMovieDetailsMapper.map(movieDetailsDto);
            }
        });
    }

    @Override
    public Observable<List<Movie>> getFavouriteMovies() {
        return moviesDao.getFavouriteMoviesList().map(new Function<List<MovieEntity>, List<Movie>>() {
            @Override
            public List<Movie> apply(List<MovieEntity> movieEntities) throws Throwable {
                return ListUtils.map(movieEntities, entityToMovieMapper::map);
            }
        });
    }

    @Override
    public Observable<Boolean> isFavouriteMovie(String id) {
        return moviesDao.isFavourite(id);
    }

    @Override
    public void addFavouriteMovie(Movie movie) {
        Log.d(TAG, "addFavouriteMovie() called with: movie = [" + movie + "]");
        Observable
                .fromCallable(new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        Log.d(TAG, "addFavourite - call() called");
                        MovieEntity movieEntity = movieToEntityMapper.map(movie);
                        moviesDao.insertMovie(movieEntity);
                        return true;
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    @Override
    public void deleteFavouriteMovie(String id) {
        Log.d(TAG, "deleteFavouriteMovie() called with: id = [" + id + "]");
        Observable
                .fromCallable(new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        Log.d(TAG, "deleteFavourite - call() called");
                        moviesDao.deleteMovie(id);
                        return true;
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}
