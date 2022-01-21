package com.regen21.moviet.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.regen21.moviet.Dao;
import com.regen21.moviet.activities.MovieActivity;
import com.regen21.moviet.models.MovieModel;
import com.regen21.moviet.R;
import com.squareup.picasso.Picasso;

public class HomeViewHolder extends RecyclerView.ViewHolder {


    public static final String IMG_BASE_URL = "https://image.tmdb.org/t/p/";
    private boolean added = false;


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

        // Movie title
        TextView textViewTitlePreview = itemView.findViewById(R.id.txt_movie_title_preview);
        textViewTitlePreview.setText(data.getTitle());

        // Year
        TextView textViewYearPreview = itemView.findViewById(R.id.txt_year_preview);
        if (data.getRelease_date().length() < 4) {
            textViewYearPreview.setText("");
        }
        else
        {
            textViewYearPreview.setText(data.getRelease_date().substring(0, 4));
        }

        // Rating
        RatingBar ratingBar = itemView.findViewById(R.id.rating_preview);
        TextView textViewRatingPreview = itemView.findViewById(R.id.txt_rating_preview);

        if(data.getVote_average()!=0) {
            ratingBar.setVisibility(View.VISIBLE);
            textViewRatingPreview.setVisibility(View.VISIBLE);
            float normalizedRating = data.getVote_average() * 0.1f;
            ratingBar.setRating(normalizedRating);
            textViewRatingPreview.setText(String.valueOf(data.getVote_average()));
        }
        else {
            ratingBar.setVisibility(View.INVISIBLE);
            textViewRatingPreview.setVisibility(View.INVISIBLE);
        }

        // Add Movie Button
        // MovIeT button
        Button addIcon = itemView.findViewById(R.id.moviet_preview_btn);
        Dao dao = new Dao();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            dao.getUserReference().child("MovIeTs").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.child(String.valueOf(data.getId())).exists()) {
                        addIcon.setBackgroundResource(R.drawable.ic_baseline_check);
                        added = true;
                    } else {
                        addIcon.setBackgroundResource(R.drawable.ic_baseline_library_add_24);
                        added = false;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

            addIcon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (!added) {
                        dao.addMoviet(data, context)
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, context.getString(R.string.error_default), Toast.LENGTH_LONG).show();
                                    }
                                });
                    } else {
                        dao.removeMoviet(String.valueOf(data.getId()))
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, context.getString(R.string.error_default), Toast.LENGTH_LONG).show();
                                    }
                                });
                    }
                }
            });
        }
        else {
            addIcon.setVisibility(View.GONE);
            itemView.findViewById(R.id.view2).setVisibility(View.GONE);
        }
    }
}