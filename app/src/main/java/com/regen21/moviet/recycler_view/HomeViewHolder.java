package com.regen21.moviet.recycler_view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.regen21.moviet.R;

public class HomeViewHolder extends RecyclerView.ViewHolder {

    public HomeViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(Integer data) {
        ImageView imageView = itemView.findViewById(R.id.home_movie_poster);
        imageView.setVisibility(View.VISIBLE);
        //data.;
    }
}