package com.diegoparra.kinoapp.data.local;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

@Dao
public interface MoviesDao {

    @Insert(onConflict = REPLACE)
    void insertAllMovies(List<MovieEntity> movieEntityList);

    @Insert(onConflict = REPLACE)
    void insertMovie(MovieEntity movieEntity);

    @Query("DELETE FROM MovieEntity WHERE id = :id")
    void deleteMovie(String id);

    @Query("SELECT * FROM MovieEntity")
    Observable<List<MovieEntity>> getFavouriteMoviesList();

    @Query("SELECT EXISTS(SELECT * FROM MovieEntity WHERE id = :id)")
    Observable<Boolean> isFavourite(String id);
}
