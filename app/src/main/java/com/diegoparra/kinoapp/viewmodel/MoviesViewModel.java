package com.diegoparra.kinoapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.diegoparra.kinoapp.data.MoviesRepository;
import com.diegoparra.kinoapp.model.Movie;

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
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<UiState> _uiState = new MutableLiveData<>();
    private MutableLiveData<List<Movie>> _movies = new MutableLiveData<>();
    private MutableLiveData<Throwable> _failure = new MutableLiveData<>();


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
                        _failure.setValue(e);
                        _uiState.setValue(UiState.ERROR);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public LiveData<UiState> getUiState() {
        return _uiState;
    }

    public LiveData<List<Movie>> getMovies() {
        return _movies;
    }

    public LiveData<Throwable> getFailure() {
        return _failure;
    }

    public enum UiState {
        LOADING, SUCCESS, ERROR
    }
}
