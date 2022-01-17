package com.regen21.moviet.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.regen21.moviet.R;
import com.regen21.moviet.activities.HomeActivity;
import com.regen21.moviet.activities.MovieListsActivity;
import com.regen21.moviet.activities.SearchMovieActivity;
import com.regen21.moviet.activities.UserActivity;
import com.regen21.moviet.activities.authentication.LoginActivity;

public class MenuHandler {

    private Activity activity;

    public MenuHandler(Activity activity) {
        this.activity = activity;

        BottomNavigationView bottomNavigationView = activity.findViewById(R.id.bottom_navigation);
        Button logoutBnt = activity.findViewById(R.id.logout_btn);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            logoutBnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(activity.getApplicationContext(), activity.getString(R.string.logout_success), Toast.LENGTH_SHORT).show();
                    logoutBnt.setVisibility(View.GONE);
                }
            });
        } else {
            logoutBnt.setVisibility(View.GONE);
        }

        //Find bottom nav view
        if (activity.getLocalClassName().equals("activities.HomeActivity")) {
            bottomNavigationView.setSelectedItemId(R.id.nav_home);
        } else if (activity.getLocalClassName().equals("activities.SearchMovieActivity")) {
            bottomNavigationView.setSelectedItemId(R.id.nav_search);
        } else if(activity.getLocalClassName().equals("activities.MovieListsActivity")) {
            bottomNavigationView.setSelectedItemId(R.id.nav_lists);
        } else {
            bottomNavigationView.setSelectedItemId(R.id.nav_profile);
        }

            //Perform ItemSelectedListener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        if (!activity.getLocalClassName().equals("activities.HomeActivity")) {
                            activity.startActivity(new Intent(activity.getApplicationContext(), HomeActivity.class));
                            activity.overridePendingTransition(0, 0);
                        }
                        break;

                    case R.id.nav_search:
                        if (!activity.getLocalClassName().equals("activities.SearchMovieActivity")) {
                            activity.startActivity(new Intent(activity.getApplicationContext(), SearchMovieActivity.class));
                            activity.overridePendingTransition(0, 0);
                        }
                        break;

                    case R.id.nav_lists:
                        if (!activity.getLocalClassName().equals("activities.MovieListsActivity")) {
                            activity.startActivity(new Intent(activity.getApplicationContext(), MovieListsActivity.class));
                            activity.overridePendingTransition(0, 0);
                        }
                        break;

                    case R.id.nav_profile:
                        if (!activity.getLocalClassName().equals("activities.UserActivity")) {
                            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                                activity.startActivity(new Intent(activity.getApplicationContext(), UserActivity.class));
                                activity.overridePendingTransition(0, 0);
                            } else {
                                Toast.makeText(activity.getApplicationContext(), "User is not logged in", Toast.LENGTH_LONG).show();
                                activity.startActivity(new Intent(activity.getApplicationContext(), LoginActivity.class));
                                activity.overridePendingTransition(0, 0);
                                activity.finish();
                            }
                        }
                        break;
                }
                return true;
            }
        });

    }
}
