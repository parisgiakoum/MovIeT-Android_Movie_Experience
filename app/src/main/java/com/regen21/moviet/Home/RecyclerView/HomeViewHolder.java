package com.regen21.moviet.Home.RecyclerView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;

import com.regen21.moviet.Movie.MovieModel;
import com.regen21.moviet.R;
import com.squareup.picasso.Picasso;

public class HomeViewHolder extends RecyclerView.ViewHolder {


    public static final String IMG_BASE_URL = "https://image.tmdb.org/t/p/";

    public HomeViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(MovieModel data) {
        ImageView imageView = itemView.findViewById(R.id.home_movie_poster);
        Picasso.get().load(IMG_BASE_URL + "w342/" + data.getPoster_path()).into(imageView);
    }
}