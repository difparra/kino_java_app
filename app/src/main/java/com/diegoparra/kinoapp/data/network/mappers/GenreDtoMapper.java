package com.diegoparra.kinoapp.data.network.mappers;

import com.diegoparra.kinoapp.data.network.GenreDto;
import com.diegoparra.kinoapp.model.Genre;
import com.diegoparra.kinoapp.utils.Mapper;

import javax.inject.Inject;

public class GenreDtoMapper implements Mapper<GenreDto, Genre> {

    @Inject
    public GenreDtoMapper() {
    }

    @Override
    public Genre map(GenreDto genreDto) {
        return new Genre(
                String.valueOf(genreDto.getId()),
                (genreDto.getName() != null) ? genreDto.getName() : "NN"
        );
    }
}
