package com.regen21.moviet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class MovieActivity extends AppCompatActivity {

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

        // Instantiate the RequestQueue.
        String url ="https://api.themoviedb.org/3/movie/550?api_key=f0bb1fa4a17a0c54c2a32720bf2fa03c";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        movieModel = new Gson().fromJson(response,MovieModel.class);
                        Log.d("TAG", movieModel.toString());

                        TextView txtMovieTitle = findViewById(R.id.txt_movie_title);
                        txtMovieTitle.setText(movieModel.getTitle());

                        TextView txtVoteAverage = findViewById(R.id.txt_vote_average);
                        txtVoteAverage.setText(String.valueOf(movieModel.getVote_average()));

                        TextView txtOverview = findViewById(R.id.txt_overview);
                        txtOverview.setText(movieModel.getOverview());

                        TextView txtRuntime = findViewById(R.id.txt_runtime);
                        txtRuntime.setText(String.valueOf(movieModel.getRuntime()));

                        TextView txtStatus = findViewById(R.id.txt_status);
                        txtStatus.setText(movieModel.getStatus());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage());
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

}