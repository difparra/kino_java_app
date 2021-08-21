package com.diegoparra.kinoapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.diegoparra.kinoapp.databinding.ListItemMovieBinding;
import com.diegoparra.kinoapp.model.Movie;

public class MoviesAdapter extends ListAdapter<Movie, MoviesAdapter.ViewHolder> {

    private final OnClickListener listener;

    public MoviesAdapter(OnClickListener listener) {
        super(diffCallback);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.from(parent, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    interface OnClickListener {
        void onItemClick(String movieId);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ListItemMovieBinding binding;
        private Movie movie;

        public ViewHolder(ListItemMovieBinding binding, OnClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (movie != null) {
                        listener.onItemClick(movie.getId());
                    }
                }
            });
        }

        static ViewHolder from(ViewGroup parent, OnClickListener listener) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new ViewHolder(ListItemMovieBinding.inflate(inflater, parent, false), listener);
        }

        public void bind(Movie movie) {
            this.movie = movie;
            Glide.with(binding.image)
                    .load(movie.getPosterUrl())
                    .into(binding.image);
        }
    }

    private static final DiffUtil.ItemCallback<Movie> diffCallback = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.equals(newItem);
        }
    };

}
