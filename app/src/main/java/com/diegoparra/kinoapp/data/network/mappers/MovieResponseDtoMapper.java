package com.diegoparra.kinoapp.data.network.mappers;

import com.diegoparra.kinoapp.data.network.MovieDto;
import com.diegoparra.kinoapp.data.network.MovieResponseDto;
import com.diegoparra.kinoapp.model.Movie;
import com.diegoparra.kinoapp.utils.Function;
import com.diegoparra.kinoapp.utils.ListUtils;
import com.diegoparra.kinoapp.utils.Mapper;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class MovieResponseDtoMapper implements Mapper<MovieResponseDto, List<Movie>> {

    private final Mapper<MovieDto, Movie> mapper;

    @Inject
    public MovieResponseDtoMapper(Mapper<MovieDto, Movie> mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Movie> map(MovieResponseDto movieResponseDto) {
        if (movieResponseDto.getResults() != null) {
            return ListUtils.map(movieResponseDto.getResults(), new Function<MovieDto, Movie>() {
                @Override
                public Movie apply(MovieDto movieDto) {
                    return mapper.map(movieDto);
                }
            });
        } else {
            return Collections.emptyList();
        }
    }

}
