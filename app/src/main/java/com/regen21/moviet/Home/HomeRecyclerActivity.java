package com.regen21.moviet.Home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.regen21.moviet.Home.RecyclerView.HomeAdapter;
import com.regen21.moviet.Movie.Credits.CreditsModel;
import com.regen21.moviet.Movie.Credits.RecyclerView.CreditsAdapter;
import com.regen21.moviet.Movie.Credits.RecyclerView.CreditsMode;
import com.regen21.moviet.Movie.MovieActivity;
import com.regen21.moviet.Movie.MovieModel;
import com.regen21.moviet.R;

import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "f0bb1fa4a17a0c54c2a32720bf2fa03c";

    private Gson gson;
    private RequestQueue queue;
    @Nullable
    private MovieModel movieModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        queue = Volley.newRequestQueue(this);
        gson = new Gson();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        String URL = BASE_URL + "movie/" + "popular" + "?api_key=" + API_KEY;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        PopularModel popularModel = gson.fromJson(response,PopularModel.class);
                        RecyclerView recyclerView = findViewById(R.id.recycleViewPopularMovies);
                        recyclerView.setAdapter(new HomeAdapter(popularModel.getResults()));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomeRecyclerActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        //recyclerView.setAdapter(new HomeAdapter(movie_posters, this));
    }

}