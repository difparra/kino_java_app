package com.diegoparra.kinoapp.di;

import com.diegoparra.kinoapp.data.FakeMoviesRepository;
import com.diegoparra.kinoapp.data.MoviesRepository;
import com.diegoparra.kinoapp.data.MoviesRepositoryImpl;
import com.diegoparra.kinoapp.data.network.MovieDto;
import com.diegoparra.kinoapp.data.network.MovieResponseDto;
import com.diegoparra.kinoapp.data.network.MovieResponseDtoMapper;
import com.diegoparra.kinoapp.model.Movie;
import com.diegoparra.kinoapp.utils.Mapper;

import java.util.List;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Singleton
    @Binds
    abstract MoviesRepository bindMoviesRepository(MoviesRepositoryImpl moviesRepository);

    @Binds
    abstract Mapper<MovieResponseDto, List<Movie>> bindMoviesMapper(MovieResponseDtoMapper mapper);

}