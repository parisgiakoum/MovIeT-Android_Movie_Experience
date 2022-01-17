package com.regen21.moviet.SearchMovie;

import android.content.Context;
import android.graphics.Color;
import android.content.Intent;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.regen21.moviet.Movie.MovieActivity;
import com.regen21.moviet.Movie.MovieModel;
import com.regen21.moviet.R;
import com.squareup.picasso.Picasso;

//Layout of a single line
public class SearchMovieViewHolder extends RecyclerView.ViewHolder {

    public static final String IMG_BASE_URL = "https://image.tmdb.org/t/p/";

    private boolean clicked = false;

    public SearchMovieViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(MovieModel data) {
        Context context = itemView.getContext();

        TextView title = itemView.findViewById(R.id.holder_list_title);
        if(!data.getTitle().isEmpty()) {
            title.setText(data.getTitle());
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MovieActivity.class);
                    intent.putExtra("movieID", data.getId());
                    context.startActivity(intent);
                }
            });
        }
        else
        {
            title.setText("");
        }

        TextView year = itemView.findViewById(R.id.holder_list_year);
        if (data.getRelease_date().length() < 4) {
            year.setText("");
        }
        else
        {
            year.setText(data.getRelease_date().substring(0, 4));
        }

        ImageView imageView = itemView.findViewById(R.id.holder_list_img);
        Picasso.get().load(IMG_BASE_URL + "w342/" + data.getPoster_path()).error(R.drawable.ic_baseline_image_24).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieActivity.class);
                intent.putExtra("movieID",data.getId());
                context.startActivity(intent);
            }
        });
        Button addIcon = itemView.findViewById(R.id.holder_list_save_icon);
        addIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!clicked) {
                    clicked = true;
                    addIcon.setBackgroundResource(R.drawable.ic_baseline_check);
                    //addIcon.setText(R.string.str_added);
                }
                else {
                    clicked = false;
                    addIcon.setBackgroundResource(R.drawable.ic_baseline_library_add_24);
                    //addIcon.setText(R.string.app_name);
                }
            }
        });


    }

}
