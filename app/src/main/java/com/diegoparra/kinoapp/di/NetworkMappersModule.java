package com.diegoparra.kinoapp.di;

import com.diegoparra.kinoapp.data.network.GenreDto;
import com.diegoparra.kinoapp.data.network.MovieDetailsDto;
import com.diegoparra.kinoapp.data.network.MovieDto;
import com.diegoparra.kinoapp.data.network.MovieResponseDto;
import com.diegoparra.kinoapp.data.network.mappers.GenreDtoMapper;
import com.diegoparra.kinoapp.data.network.mappers.MovieDetailsDtoMapper;
import com.diegoparra.kinoapp.data.network.mappers.MovieDtoMapper;
import com.diegoparra.kinoapp.data.network.mappers.MovieResponseDtoMapper;
import com.diegoparra.kinoapp.model.Genre;
import com.diegoparra.kinoapp.model.Movie;
import com.diegoparra.kinoapp.model.MovieDetails;
import com.diegoparra.kinoapp.utils.Mapper;

import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class NetworkMappersModule {

    @Binds
    abstract Mapper<MovieResponseDto, List<Movie>> bindMoviesResponseMapper(MovieResponseDtoMapper mapper);

    @Binds
    abstract Mapper<MovieDto, Movie> bindMoviesMapper(MovieDtoMapper movieDtoMapper);

    @Binds
    abstract Mapper<GenreDto, Genre> bindGenreMapper(GenreDtoMapper genreDtoMapper);

    @Binds
    abstract Mapper<MovieDetailsDto, MovieDetails> bindMovieDetailsMapper(MovieDetailsDtoMapper movieDetailsDtoMapper);



}
