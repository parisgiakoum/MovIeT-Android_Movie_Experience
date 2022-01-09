package com.regen21.moviet.recycler_view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.regen21.moviet.R;

import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        RecyclerView recyclerView = findViewById(R.id.recycleViewPopularMovies);

        //recyclerView.setAdapter(new HomeAdapter(movie_posters, this));
    }

}