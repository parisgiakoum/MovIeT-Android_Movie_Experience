package com.regen21.moviet.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
import com.regen21.moviet.R;
import com.regen21.moviet.adapters.SearchMovieAdapter;
import com.regen21.moviet.utils.MenuHandler;

public class SearchMovieActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "f0bb1fa4a17a0c54c2a32720bf2fa03c";

    private Gson gson;
    private RequestQueue queue;
    private RecyclerView recyclerView;
    private MovieResultsModel model;

    // Do nothing on Back btn
    @Override
    public void onBackPressed() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search);

        MenuHandler menuHandler = new MenuHandler(this);

        SearchView searchView = findViewById(R.id.search);

        recyclerView = findViewById(R.id.recycleViewMovieList);
        queue = Volley.newRequestQueue(this);
        gson = new Gson();

    }

    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setPopular();

        SearchView searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    setPopular();
                }
                else {
                    setSearch(newText);
                }
                return false;
            }
        });
}

    public void setSearch(String query) {
        //Send most popular movies request
        String URL = BASE_URL + "search/movie" + "?api_key=" + API_KEY + "&query=" + query;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        model = gson.fromJson(response, MovieResultsModel.class);
                        recyclerView.setAdapter(new SearchMovieAdapter(model.getResults()));
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

    public void setPopular() {
        //Send most popular movies request
        String URL = BASE_URL + "movie/" + "popular" + "?api_key=" + API_KEY;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        model = gson.fromJson(response, MovieResultsModel.class);
                        recyclerView.setAdapter(new SearchMovieAdapter(model.getResults()));
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