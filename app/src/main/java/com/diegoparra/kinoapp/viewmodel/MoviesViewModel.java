package com.diegoparra.kinoapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.diegoparra.kinoapp.data.MoviesRepository;
import com.diegoparra.kinoapp.model.Movie;
import com.diegoparra.kinoapp.utils.Event;
import com.diegoparra.kinoapp.utils.UiState;

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
public class MoviesViewModel extends ViewModel {

    private final MoviesRepository moviesRepo;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<UiState> _uiState = new MutableLiveData<>();
    private final MutableLiveData<List<Movie>> _movies = new MutableLiveData<>();
    private final MutableLiveData<Event<Throwable>> _failure = new MutableLiveData<>();


    @Inject
    public MoviesViewModel(MoviesRepository moviesRepo) {
        this.moviesRepo = moviesRepo;
        subscribeMovieList();
    }

    private void subscribeMovieList() {
        moviesRepo.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Movie>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                        _uiState.postValue(UiState.LOADING);
                    }

                    @Override
                    public void onNext(@NonNull List<Movie> movies) {
                        _movies.setValue(movies);
                        _uiState.setValue(UiState.SUCCESS);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        _failure.setValue(new Event(e));
                        _uiState.setValue(UiState.ERROR);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public LiveData<UiState> getUiState() {
        return _uiState;
    }

    public LiveData<List<Movie>> getMovies() {
        return _movies;
    }

    public LiveData<Event<Throwable>> getFailure() {
        return _failure;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
