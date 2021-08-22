package com.diegoparra.kinoapp.di;

import androidx.annotation.NonNull;

import com.bumptech.glide.RequestBuilder;
import com.diegoparra.kinoapp.BuildConfig;
import com.diegoparra.kinoapp.data.network.MovieDto;
import com.diegoparra.kinoapp.data.network.MovieResponseDto;
import com.diegoparra.kinoapp.data.network.MoviesService;
import com.diegoparra.kinoapp.data.network.mappers.MovieDtoMapper;
import com.diegoparra.kinoapp.data.network.mappers.MovieResponseDtoMapper;
import com.diegoparra.kinoapp.model.Movie;
import com.diegoparra.kinoapp.utils.Mapper;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Locale;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_components_ActivityRetainedComponent;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Singleton
    @Provides
    @LoggingInterceptor
    static Interceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    @Singleton
    @Provides
    @CustomInterceptor
    static Interceptor provideCustomInterceptor() {
        return new Interceptor() {
            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalUrl = original.url();
                HttpUrl url = originalUrl.newBuilder()
                        .addQueryParameter("api_key", BuildConfig.TMDB_KEY)
                        .addQueryParameter("language", Locale.getDefault().getLanguage())
                        .build();
                Request.Builder requestBuilder = original.newBuilder().url(url);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
    }

    @Singleton
    @Provides
    static OkHttpClient providesOkHttpClient(@LoggingInterceptor Interceptor loggingInterceptor, @CustomInterceptor Interceptor customInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(customInterceptor)
                .build();
    }

    @Singleton
    @Provides
    static MoviesService provideMoviesService(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(MoviesService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(MoviesService.class);
    }

}


@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@interface LoggingInterceptor {}

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@interface CustomInterceptor {}

