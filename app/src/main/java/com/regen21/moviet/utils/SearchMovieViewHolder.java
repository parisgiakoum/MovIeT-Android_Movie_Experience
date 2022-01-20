package com.regen21.moviet.utils;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.regen21.moviet.Dao;
import com.regen21.moviet.activities.MovieActivity;
import com.regen21.moviet.models.MovieModel;
import com.regen21.moviet.R;
import com.squareup.picasso.Picasso;

// TODO FIX NOONE LOGGEDIN
//Layout of a single line
public class SearchMovieViewHolder extends RecyclerView.ViewHolder {

    public static final String IMG_BASE_URL = "https://image.tmdb.org/t/p/";
    private boolean added = false;

    private Context context;

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

        // MovIeT button
        Button addIcon = itemView.findViewById(R.id.holder_list_save_icon);
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
        }
    }

}
