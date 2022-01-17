package com.regen21.moviet.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.regen21.moviet.activities.MovieActivity;
import com.regen21.moviet.models.MovieModel;
import com.regen21.moviet.R;
import com.squareup.picasso.Picasso;

public class HomeViewHolder extends RecyclerView.ViewHolder {


    public static final String IMG_BASE_URL = "https://image.tmdb.org/t/p/";

    public HomeViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(MovieModel data) {
        Context context = itemView.getContext();
        ImageView imageView = itemView.findViewById(R.id.home_movie_poster);
        Picasso.get().load(IMG_BASE_URL + "w342/" + data.getPoster_path()).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieActivity.class);
                intent.putExtra("movieID",data.getId());
                context.startActivity(intent);
            }
        });
    }
}