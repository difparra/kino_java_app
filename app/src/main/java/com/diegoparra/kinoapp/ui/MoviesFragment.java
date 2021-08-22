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
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import com.diegoparra.kinoapp.databinding.FragmentMoviesBinding;
import com.diegoparra.kinoapp.model.Movie;
import com.diegoparra.kinoapp.utils.ListUtils;
import com.diegoparra.kinoapp.viewmodel.MoviesViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MoviesFragment extends Fragment {

    private MoviesViewModel viewModel;
    private FragmentMoviesBinding binding;
    private MoviesAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MoviesViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMoviesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter = new MoviesAdapter(new MoviesAdapter.OnClickListener() {
            @Override
            public void onItemClick(String movieId) {
                NavDirections navDirections = MoviesFragmentDirections.actionGlobalMovieDetailsFragment(movieId);
                NavHostFragment.findNavController(MoviesFragment.this).navigate(navDirections);
            }
        });
        binding.moviesList.setHasFixedSize(true);
        binding.moviesList.setAdapter(adapter);

        subscribeUi();
    }

    private void subscribeUi() {
        viewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                adapter.submitList(movies);
            }
        });
        viewModel.getFailure().observe(getViewLifecycleOwner(), new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                adapter.submitList(Collections.emptyList());

                String errorMessage;
                if (throwable.getMessage() != null) {
                    errorMessage = throwable.getMessage();
                } else {
                    errorMessage = "Some failure has happened.";
                }
                Snackbar.make(binding.getRoot(), errorMessage, Snackbar.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
