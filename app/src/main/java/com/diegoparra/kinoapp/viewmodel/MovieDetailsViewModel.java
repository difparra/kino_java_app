package com.diegoparra.kinoapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.diegoparra.kinoapp.data.MoviesRepository;
import com.diegoparra.kinoapp.model.MovieDetails;
import com.diegoparra.kinoapp.utils.Event;
import com.diegoparra.kinoapp.utils.UiState;

import java.util.Objects;

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

    private static final String TAG = "MovieDetailsViewModel";

    private final String id;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MoviesRepository moviesRepository;
    private final MutableLiveData<UiState> _uiState = new MutableLiveData<>();
    private final MutableLiveData<MovieDetails> _movieDetails = new MutableLiveData<>();
    private final MutableLiveData<Event<Throwable>> _failure = new MutableLiveData<>();
    private final MutableLiveData<Boolean> _isFavourite = new MutableLiveData<>(false);
    private final MutableLiveData<Event<Boolean>> _toastAddingRemovingFavourite = new MutableLiveData<>();

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
                        _uiState.postValue(UiState.SUCCESS);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        _failure.setValue(new Event<>(e));
                        _uiState.postValue(UiState.ERROR);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        moviesRepository.isFavouriteMovie(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull Boolean aBoolean) {
                        _isFavourite.setValue(aBoolean);
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

    public void toggleFavouriteState() {
        Log.d(TAG, "toggleFavouriteState() called");
        if (_isFavourite.getValue() == null || _movieDetails.getValue() == null) {
            Log.e(TAG, "toggleFavouriteState: Either _isFavourite or _movieDetails is null");
            return;
        }
        if (_isFavourite.getValue()) {
            Log.d(TAG, "isFavourite = true, calling delete favourite movie...");
            moviesRepository.deleteFavouriteMovie(id);
            _toastAddingRemovingFavourite.setValue(new Event<>(false));
        } else {
            Log.d(TAG, "isFavourite = false, calling add favourite movie...");
            moviesRepository.addFavouriteMovie(_movieDetails.getValue().toMovie());
            _toastAddingRemovingFavourite.setValue(new Event<>(true));
        }
    }


    public LiveData<UiState> getUiState() {
        return _uiState;
    }

    public LiveData<MovieDetails> getMovieDetails() {
        return _movieDetails;
    }

    public LiveData<Boolean> isFavourite() {
        return _isFavourite;
    }

    public LiveData<Event<Throwable>> getFailure() {
        return _failure;
    }

    public LiveData<Event<Boolean>> getToastAddingRemovingFavourite() {
        return _toastAddingRemovingFavourite;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
