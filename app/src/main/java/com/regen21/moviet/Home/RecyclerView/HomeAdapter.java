package com.regen21.moviet.Home.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.regen21.moviet.Movie.MovieModel;
import com.regen21.moviet.R;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter <HomeViewHolder> {

    private List<MovieModel> movies;

    public HomeAdapter(List<MovieModel> movies){
        this.movies = movies;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_home_movie_item, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        MovieModel data = movies.get(position);
        holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


}