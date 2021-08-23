package com.diegoparra.kinoapp.data.local;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(
        entities = {MovieEntity.class},
        version = 1,
        exportSchema = false
)
@TypeConverters(MovieConverters.class)
public abstract class MoviesDatabase extends RoomDatabase {

    public static final String DB_NAME = "movies_database";

    public abstract MoviesDao getMoviesDao();

}
