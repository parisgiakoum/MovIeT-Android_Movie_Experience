package com.regen21.moviet.UserProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.regen21.moviet.Home.HomeActivity;
import com.regen21.moviet.MovieLists.MovieListsActivity;
import com.regen21.moviet.R;
import com.regen21.moviet.SearchMovie.SearchMovieActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //Find bottom nav view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //
        bottomNavigationView.setSelectedItemId(R.id.nav_profile);

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
                        startActivity(new Intent(getApplicationContext(), SearchMovieActivity.class));
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_lists:
                        startActivity(new Intent(getApplicationContext(), MovieListsActivity.class));
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_profile:
                        break;
                }
                return true;
            }
        });
    }
}