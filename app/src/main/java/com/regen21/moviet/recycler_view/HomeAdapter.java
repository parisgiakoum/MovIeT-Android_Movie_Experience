package com.regen21.moviet.recycler_view;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.regen21.moviet.R;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter <HomeViewHolder> {

    private List<String> moviesImg;

    public HomeAdapter(List<String> moviesImg, Context context){
        this.moviesImg = moviesImg;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_home_movie_item, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return moviesImg.size();
    }
}