package com.regen21.moviet.activities;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;

import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.gson.Gson;
import com.regen21.moviet.models.MovieResultsModel;
import com.regen21.moviet.adapters.HomeAdapter;
import com.regen21.moviet.R;
import com.regen21.moviet.utils.MenuHandler;

public class HomeActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "f0bb1fa4a17a0c54c2a32720bf2fa03c";

    private Gson gson;
    private RequestQueue queue;

    // Do nothing on Back btn
    @Override
    public void onBackPressed() {    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        queue = Volley.newRequestQueue(this);
        gson = new Gson();

        MenuHandler menuHandler = new MenuHandler(this);
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
                        MovieResultsModel popularModel = gson.fromJson(response, MovieResultsModel.class);
                        RecyclerView recyclerView = findViewById(R.id.recycleViewPopularMovies);
                        recyclerView.setAdapter(new HomeAdapter(popularModel.getResults()));
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
                        MovieResultsModel topRatedModel = gson.fromJson(response,MovieResultsModel.class);
                        RecyclerView recyclerView = findViewById(R.id.recycleViewTopRated);
                        recyclerView.setAdapter(new HomeAdapter(topRatedModel.getResults()));
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
                        MovieResultsModel upcomingModel = gson.fromJson(response,MovieResultsModel.class);
                        RecyclerView recyclerView = findViewById(R.id.recycleViewUpcoming);
                        recyclerView.setAdapter(new HomeAdapter(upcomingModel.getResults()));
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