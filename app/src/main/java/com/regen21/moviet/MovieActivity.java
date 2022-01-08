package com.regen21.moviet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "f0bb1fa4a17a0c54c2a32720bf2fa03c";
    private static final String IMG_BASE_URL = "https://image.tmdb.org/t/p/w500/";


    private RequestQueue queue;
    @Nullable
    private MovieModel movieModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        queue = Volley.newRequestQueue(this);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        int movieID=500;

        // Instantiate the RequestQueue.
        sendIdRequest(movieID);

    }

    public void sendIdRequest(int id) {
        String movieURL = BASE_URL + "movie/" + id + "?api_key=" + API_KEY;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, movieURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        setMovie(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MovieActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @SuppressLint("DefaultLocale")
    public void setMovie(String response) {
        // Display the first 500 characters of the response string.
        movieModel = new Gson().fromJson(response,MovieModel.class);

        TextView txtMovieTitle = findViewById(R.id.txt_movie_title);
        txtMovieTitle.setText(movieModel.getTitle());

        TextView txtVoteAverage = findViewById(R.id.txt_vote_average);
        txtVoteAverage.setText(String.format("%s: %s/10",getResources().getString(R.string.str_rating),String.valueOf(movieModel.getVote_average())));

        TextView txtOverview = findViewById(R.id.txt_overview);
        txtOverview.setText(movieModel.getOverview());

        TextView txtRuntime = findViewById(R.id.txt_runtime);
        txtRuntime.setText(String.format("%s: %d %s",getResources().getString(R.string.str_duration),movieModel.getRuntime(),getResources().getString(R.string.str_min)));

        TextView txtStatus = findViewById(R.id.txt_status);
        txtStatus.setText(movieModel.getStatus());

        ImageView imgPoster = findViewById(R.id.img_poster);
        Picasso.get().load(IMG_BASE_URL + movieModel.getPoster_path()).into(imgPoster);
    }




}