package com.regen21.moviet.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.regen21.moviet.models.MovieModel;
import com.regen21.moviet.R;
import com.regen21.moviet.utils.SearchMovieViewHolder;

import java.util.List;

public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieViewHolder> {
    private List<MovieModel> movies;

    public SearchMovieAdapter(List<MovieModel> movies){
        this.movies = movies;
    }

    @NonNull
    @Override
    public SearchMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_movie_search_item, parent, false);
        return new SearchMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMovieViewHolder holder, int position) {
        MovieModel data = movies.get(position);
        holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}

