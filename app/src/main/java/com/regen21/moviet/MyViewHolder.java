package com.regen21.moviet;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class MyViewHolder extends RecyclerView.ViewHolder {

    public static final String IMG_BASE_URL = "https://image.tmdb.org/t/p/";

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(CastModel data) {
        TextView textView = itemView.findViewById(R.id.txt_profile_name);
        textView.setText(data.getName());
        ImageView imageView = itemView.findViewById(R.id.img_profile);
        if (data.getProfile_path() != null) {
            Picasso.get().load(IMG_BASE_URL + "w342/" + data.getProfile_path()).into(imageView);
        }
    }
}
