package com.regen21.moviet.Home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
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
import com.regen21.moviet.Home.RecyclerView.HomeAdapter;
import com.regen21.moviet.MovieLists.MovieListsActivity;
import com.regen21.moviet.R;
import com.regen21.moviet.SearchMovie.SearchMovieActivity;
import com.regen21.moviet.UserProfile.UserActivity;

public class HomeActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "f0bb1fa4a17a0c54c2a32720bf2fa03c";

    private Gson gson;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        //Perform ItemSelectedListener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
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
                        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
                            startActivity(new Intent(getApplicationContext(), UserActivity.class));
                            overridePendingTransition(0, 0);
                            break;
                        }
                        else {
                            Toast.makeText(HomeActivity.this, "User is not logged in", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                            overridePendingTransition(0, 0);
                            finish();
                            break;
                        }
                }
                return true;
            }
        });


    }

    @Override
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
                        RecyclerView recyclerView = findViewById(R.id.recycleViewPopularMovies);
                        recyclerView.setAdapter(new HomeAdapter(popularModel.getPopular()));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomeActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


        //Send top rated movies request
        URL = BASE_URL + "movie/" + "top_rated" + "?api_key=" + API_KEY;

        // Request a string response from the provided URL.
        stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        TopRatedModel topRatedModel = gson.fromJson(response,TopRatedModel.class);
                        RecyclerView recyclerView = findViewById(R.id.recycleViewTopRated);
                        recyclerView.setAdapter(new HomeAdapter(topRatedModel.getTopRated()));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomeActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


        //Send upcoming movies request
        URL = BASE_URL + "movie/" + "upcoming" + "?api_key=" + API_KEY;

        // Request a string response from the provided URL.
        stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        UpcomingModel upcomingModel = gson.fromJson(response,UpcomingModel.class);
                        RecyclerView recyclerView = findViewById(R.id.recycleViewUpcoming);
                        recyclerView.setAdapter(new HomeAdapter(upcomingModel.getUpcoming()));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomeActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }





}