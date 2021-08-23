package com.diegoparra.kinoapp.di;

import android.content.Context;

import androidx.room.Room;

import com.diegoparra.kinoapp.data.local.MoviesDao;
import com.diegoparra.kinoapp.data.local.MoviesDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class LocalModule {

    @Singleton
    @Provides
    static MoviesDatabase providesMovieDatabase(@ApplicationContext Context appContext) {
        return Room.databaseBuilder(
                appContext,
                MoviesDatabase.class,
                MoviesDatabase.DB_NAME
        ).build();
    }

    @Singleton
    @Provides
    static MoviesDao providesMoviesDao(MoviesDatabase moviesDatabase) {
        return moviesDatabase.getMoviesDao();
    }

}
