package com.diegoparra.kinoapp.data.network;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface MoviesService {

    String BASE_URL = "https://api.themoviedb.org/3/";

    @GET("movie/popular")
    Observable<MovieResponseDto> getMovies();

}
