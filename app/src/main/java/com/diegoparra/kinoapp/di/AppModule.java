package com.diegoparra.kinoapp.di;

import com.diegoparra.kinoapp.data.FakeMoviesRepository;
import com.diegoparra.kinoapp.data.MoviesRepository;
import com.diegoparra.kinoapp.data.MoviesRepositoryImpl;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Binds
    abstract MoviesRepository bindMoviesRepository(FakeMoviesRepository moviesRepository);

}
