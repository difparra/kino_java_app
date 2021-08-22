package com.diegoparra.kinoapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.diegoparra.kinoapp.data.MoviesRepository;
import com.diegoparra.kinoapp.model.MovieDetails;
import com.diegoparra.kinoapp.utils.UiState;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class MovieDetailsViewModel extends ViewModel {

    private final String id;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MoviesRepository moviesRepository;
    private final MutableLiveData<UiState> _uiState = new MutableLiveData<>();
    private final MutableLiveData<MovieDetails> _movieDetails = new MutableLiveData<>();
    private final MutableLiveData<Throwable> _failure = new MutableLiveData<>();


    @Inject
    public MovieDetailsViewModel(MoviesRepository moviesRepository, SavedStateHandle savedStateHandle) {
        this.moviesRepository = moviesRepository;
        this.id = savedStateHandle.get("movie_id");
        subscribeMovieDetails();
    }

    private void subscribeMovieDetails() {
        moviesRepository.getMovieDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieDetails>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                        _uiState.postValue(UiState.LOADING);
                    }

                    @Override
                    public void onNext(@NonNull MovieDetails movieDetails) {
                        _movieDetails.setValue(movieDetails);
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


    public LiveData<UiState> getUiState() {
        return _uiState;
    }

    public LiveData<MovieDetails> getMovieDetails() {
        return _movieDetails;
    }

    public LiveData<Throwable> getFailure() {
        return _failure;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
