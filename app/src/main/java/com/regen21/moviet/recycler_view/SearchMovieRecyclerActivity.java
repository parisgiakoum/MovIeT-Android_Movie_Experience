package com.regen21.moviet.recycler_view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.regen21.moviet.R;

import java.util.ArrayList;
import java.util.List;

public class SearchMovieRecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list_recycler);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        RecyclerView recyclerView = findViewById(R.id.recycleViewMovieList);

        recyclerView.setAdapter(new SearchMovieAdapter(getDataList(), this));
    }

    private List<String> getDataList(){
        List<String> list = new ArrayList<>();
        for (int i=0; i<11; i++){
            list.add(String.valueOf(i));
        }
        return list;
    }
}