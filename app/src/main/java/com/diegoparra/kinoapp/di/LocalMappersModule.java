package com.diegoparra.kinoapp.di;

import com.diegoparra.kinoapp.data.local.EntityToMovieMapper;
import com.diegoparra.kinoapp.data.local.MovieEntity;
import com.diegoparra.kinoapp.data.local.MovieToEntityMapper;
import com.diegoparra.kinoapp.model.Movie;
import com.diegoparra.kinoapp.utils.Mapper;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class LocalMappersModule {

    @Binds
    abstract Mapper<Movie, MovieEntity> bindMovieToEntityMapper(MovieToEntityMapper mapper);

    @Binds
    abstract Mapper<MovieEntity, Movie> bindMovieEntityMapper(EntityToMovieMapper mapper);

}
