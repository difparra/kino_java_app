package com.diegoparra.kinoapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.diegoparra.kinoapp.data.MoviesRepository;
import com.diegoparra.kinoapp.model.Movie;
import com.diegoparra.kinoapp.utils.Event;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class FavouritesViewModel extends ViewModel {

    private final MoviesRepository moviesRepository;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<List<Movie>> _favourites = new MutableLiveData<>();
    private final MutableLiveData<Event<Throwable>> _failure = new MutableLiveData<>();

    @Inject
    public FavouritesViewModel(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
        subscribeMovies();
    }

    private void subscribeMovies() {
        moviesRepository.getFavouriteMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Movie>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull List<Movie> movies) {
                        _favourites.setValue(movies);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        _failure.setValue(new Event<>(e));
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    public LiveData<List<Movie>> getFavourites() {
        return _favourites;
    }

    public LiveData<Event<Throwable>> getFailure() {
        return _failure;
    }
}
