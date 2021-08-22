package com.diegoparra.kinoapp.di;

import com.diegoparra.kinoapp.data.MoviesRepository;
import com.diegoparra.kinoapp.data.MoviesRepositoryImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Singleton
    @Binds
    abstract MoviesRepository bindMoviesRepository(MoviesRepositoryImpl moviesRepository);

}