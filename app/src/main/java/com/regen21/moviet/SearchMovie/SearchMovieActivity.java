package com.regen21.moviet.SearchMovie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.regen21.moviet.Authentication.LoginActivity;
import com.regen21.moviet.Home.HomeActivity;
import com.regen21.moviet.Home.PopularModel;
import com.regen21.moviet.Home.RecyclerView.HomeAdapter;
import com.regen21.moviet.MovieLists.MovieListsActivity;
import com.regen21.moviet.R;
import com.regen21.moviet.UserProfile.UserActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchMovieActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "f0bb1fa4a17a0c54c2a32720bf2fa03c";

    private Gson gson;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search);

        SearchView searchView = findViewById(R.id.search);

        queue = Volley.newRequestQueue(this);
        gson = new Gson();

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

    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


        //Send most popular movies request
        String URL = BASE_URL + "movie/" + "popular" + "?api_key=" + API_KEY;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        PopularModel popularModel = gson.fromJson(response,PopularModel.class);
                        RecyclerView recyclerView = findViewById(R.id.recycleViewMovieList);
                        recyclerView.setAdapter(new SearchMovieAdapter(popularModel.getPopular()));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}