package com.regen21.moviet.SearchMovie;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.regen21.moviet.Movie.MovieActivity;
import com.regen21.moviet.Movie.MovieModel;
import com.regen21.moviet.R;
import com.squareup.picasso.Picasso;

//Layout of a single line
public class SearchMovieViewHolder extends RecyclerView.ViewHolder {

    public static final String IMG_BASE_URL = "https://image.tmdb.org/t/p/";

    public SearchMovieViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(MovieModel data) {
        Context context = itemView.getContext();

        TextView title = itemView.findViewById(R.id.holder_list_title);
        title.setText(data.getTitle());
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieActivity.class);
                intent.putExtra("movieID",data.getId());
                context.startActivity(intent);
            }
        });

        TextView year = itemView.findViewById(R.id.holder_list_year);
        year.setText(data.getRelease_date().substring(0,4));

        ImageView imageView = itemView.findViewById(R.id.holder_list_img);
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
