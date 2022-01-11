package com.regen21.moviet.SearchMovie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.regen21.moviet.Home.HomeActivity;
import com.regen21.moviet.MovieLists.MovieListsActivity;
import com.regen21.moviet.R;
import com.regen21.moviet.UserProfile.UserActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search);

        //Find bottom nav view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);


        bottomNavigationView.setSelectedItemId(R.id.nav_search);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_search:
                        break;

                    case R.id.nav_lists:
                        startActivity(new Intent(getApplicationContext(), MovieListsActivity.class));
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_profile:
                        startActivity(new Intent(getApplicationContext(), UserActivity.class));
                        overridePendingTransition(0, 0);
                        break;
                }
                return true;
            }
        });


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