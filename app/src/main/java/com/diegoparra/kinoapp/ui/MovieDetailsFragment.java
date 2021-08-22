package com.diegoparra.kinoapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.diegoparra.kinoapp.databinding.FragmentMovieDetailsBinding;
import com.diegoparra.kinoapp.model.Genre;
import com.diegoparra.kinoapp.model.MovieDetails;
import com.diegoparra.kinoapp.utils.Event;
import com.diegoparra.kinoapp.utils.ListUtils;
import com.diegoparra.kinoapp.viewmodel.MovieDetailsViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MovieDetailsFragment extends Fragment {

    private MovieDetailsViewModel viewModel;
    private FragmentMovieDetailsBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel.getMovieDetails().observe(getViewLifecycleOwner(), new Observer<MovieDetails>() {
            @Override
            public void onChanged(MovieDetails movieDetails) {
                Glide.with(binding.appbarImage)
                        .load(movieDetails.getBackdropUrl())
                        .into(binding.appbarImage);
                binding.collapsingToolbar.setTitle(movieDetails.getTitle());
                setRating(movieDetails.getRating());
                binding.details.setText(getDetails(movieDetails.getReleaseDate(), movieDetails.getRuntime(), movieDetails.getGenres()));
                binding.overview.setText(movieDetails.getOverview());
            }
        });
        viewModel.getFailure().observe(getViewLifecycleOwner(), new Observer<Event<Throwable>>() {
            @Override
            public void onChanged(Event<Throwable> throwableEvent) {
                Throwable failure = throwableEvent.getContentIfNotHandled();
                if(failure!=null) {
                    String errorMessage = (failure.getMessage() != null) ? failure.getMessage() : "Some failure has happened";
                    Snackbar.make(binding.getRoot(), errorMessage, Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setRating(float rating) {
        binding.ratingBar.setRating(rating/2);
        binding.ratingText.setText("(" + (int)(rating*10) + "%)");
    }

    private String getDetails(LocalDate releaseDate, int runtimeMinutes, List<Genre> genres) {
        String year = String.valueOf(releaseDate.getYear());
        int hours = runtimeMinutes / 60;
        int minutes = runtimeMinutes % 60;
        String runtime = ((hours > 0) ? hours + "h " : "") + minutes + "m";
        String genresString = ListUtils.joinToString(genres, Genre::getName, ", ");
        return year + " • " + runtime + " • " + genresString;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
