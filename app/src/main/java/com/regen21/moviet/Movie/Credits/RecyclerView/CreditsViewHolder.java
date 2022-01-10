package com.regen21.moviet.Movie.Credits.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.regen21.moviet.Movie.Credits.CastModel;
import com.regen21.moviet.Movie.Credits.CrewModel;
import com.regen21.moviet.R;
import com.squareup.picasso.Picasso;

public class CreditsViewHolder extends RecyclerView.ViewHolder {

    public static final String IMG_BASE_URL = "https://image.tmdb.org/t/p/";

    public CreditsViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(CastModel castData, CrewModel crewData, CreditsMode mode) {
        TextView textNameView = itemView.findViewById(R.id.txt_profile_name);
        TextView textJobView = itemView.findViewById(R.id.txt_profile_job);
        ImageView imageView = itemView.findViewById(R.id.img_profile);
        if (mode == CreditsMode.CAST) {
            textNameView.setText(castData.getName());
            textJobView.setText(castData.getCharacter());
            Picasso.get().load(IMG_BASE_URL + "w342/" + castData.getProfile_path()).error(R.drawable.ic_baseline_person_24).into(imageView);
        } else {
            textNameView.setText(crewData.getName());
            textJobView.setText(crewData.getJob());
            Picasso.get().load(IMG_BASE_URL + "w342/" + crewData.getProfile_path()).error(R.drawable.ic_baseline_person_24).into(imageView);
        }
    }
}
