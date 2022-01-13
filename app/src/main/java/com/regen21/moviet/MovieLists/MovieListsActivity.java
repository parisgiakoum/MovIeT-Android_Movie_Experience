package com.regen21.moviet.MovieLists;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.regen21.moviet.Authentication.LoginActivity;
import com.regen21.moviet.Home.HomeActivity;
import com.regen21.moviet.R;
import com.regen21.moviet.SearchMovie.SearchMovieActivity;
import com.regen21.moviet.UserProfile.UserActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MovieListsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);


        // MENUS
        Button logoutBnt = findViewById(R.id.logout_btn);
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            logoutBnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(getApplicationContext(), getString(R.string.logout_success), Toast.LENGTH_SHORT).show();
                    logoutBnt.setVisibility(View.GONE);
                }
            });
        }
        else {
            logoutBnt.setVisibility(View.GONE);
        }

        //Find bottom nav view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_lists);

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
                        break;

                    case R.id.nav_profile:
                        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
                            startActivity(new Intent(getApplicationContext(), UserActivity.class));
                            overridePendingTransition(0, 0);
                            break;
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "User is not logged in", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            overridePendingTransition(0, 0);
                            finish();
                            break;
                        }
                }
                return true;
            }
        });
    }
}