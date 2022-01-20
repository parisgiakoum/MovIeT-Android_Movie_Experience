package com.regen21.moviet.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.regen21.moviet.Dao;
import com.regen21.moviet.models.MovieResultsModel;
import com.regen21.moviet.adapters.HomeAdapter;
import com.regen21.moviet.adapters.CreditsAdapter;
import com.regen21.moviet.adapters.CreditsMode;
import com.regen21.moviet.models.creditModels.CreditsModel;
import com.regen21.moviet.models.MovieModel;
import com.regen21.moviet.R;
import com.squareup.picasso.Picasso;


public class MovieActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "f0bb1fa4a17a0c54c2a32720bf2fa03c";
    public static final String IMG_BASE_URL = "https://image.tmdb.org/t/p/";

    private Gson gson;
    private RequestQueue queue;
    private boolean added = false;
    private int movieID;
    private MovieModel movieModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Button backBtn = findViewById(R.id.movie_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });

        queue = Volley.newRequestQueue(this);
        gson = new Gson();

        Intent intent = getIntent();
        movieID = intent.getIntExtra("movieID",500);

    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        implementFabFunctionality();

        sendAPIRequest(movieID);

    }

    public void sendAPIRequest(int id) {
        // Send Movie info request
        String URL = BASE_URL + "movie/" + id + "?api_key=" + API_KEY;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
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


        // Send movie credits request
        URL = BASE_URL + "movie/" + id + "/credits" + "?api_key=" + API_KEY;

        // Request a string response from the provided URL.
        stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        CreditsModel creditsModel = gson.fromJson(response,CreditsModel.class);
                        if (!creditsModel.getCast().isEmpty()) {
                            TextView textViewCast = findViewById(R.id.txt_cast);
                            textViewCast.setText(getString(R.string.str_cast));
                            RecyclerView castRecyclerView = findViewById(R.id.cast_recycler);
                            castRecyclerView.setAdapter(new CreditsAdapter(creditsModel.getCast(), null, CreditsMode.CAST));
                        }
                        if (!creditsModel.getCrew().isEmpty()) {
                            TextView textViewCrew = findViewById(R.id.txt_crew);
                            textViewCrew.setText(getString(R.string.str_crew));
                            RecyclerView crewRecyclerView = findViewById(R.id.crew_recycler);
                            crewRecyclerView.setAdapter(new CreditsAdapter(null, creditsModel.getCrew(), CreditsMode.CREW));
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MovieActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        // Send movie recommendation request
        URL = BASE_URL + "movie/" + id + "/recommendations" + "?api_key=" + API_KEY;

        // Request a string response from the provided URL.
        stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MovieResultsModel movieResultsModel = gson.fromJson(response, MovieResultsModel.class);
                        if (!movieResultsModel.getResults().isEmpty()) {
                            TextView textViewRecommendation = findViewById(R.id.txt_recommendation);
                            textViewRecommendation.setText(getString(R.string.str_recommendations));
                            RecyclerView recommendationRecyclerView = findViewById(R.id.recommendation_recycler);
                            recommendationRecyclerView.setAdapter(new HomeAdapter(movieResultsModel.getResults()));
                        }
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

        // Set basic movie UI
        movieModel = gson.fromJson(response,MovieModel.class);

        TextView txtMovieTitle = findViewById(R.id.txt_movie_title);
        txtMovieTitle.setText(movieModel.getTitle());

        if(movieModel.getTagline()!=null) {
            TextView txtTagline = findViewById(R.id.txt_tagline);
            txtTagline.setText(movieModel.getTagline());
        }

        RatingBar ratingBar = findViewById(R.id.rating);
        if(movieModel.getVote_average()!=0) {
            ratingBar.setRating(movieModel.getVote_average());
            TextView txtVoteAverage = findViewById(R.id.txt_vote_average);
            txtVoteAverage.setText(String.valueOf(movieModel.getVote_average()));
            TextView txtVoteCount = findViewById(R.id.txt_vote_count);
            txtVoteCount.setText("(" + movieModel.getVote_count() + " " + getString(R.string.str_votes) + ")");
        } else {
            ratingBar.setVisibility(View.GONE);
        }

        if (!movieModel.getGenres().isEmpty()) {
            String genres = movieModel.getGenres().get(0).getName();
            for (int i = 1; i < movieModel.getGenres().size(); i++) {
                genres = genres + " - " + movieModel.getGenres().get(i).getName();
            }
            TextView txtGenres = findViewById(R.id.txt_genres);
            txtGenres.setText(genres);
        }

        TextView txtOverview = findViewById(R.id.txt_overview);
        if(movieModel.getOverview()!=null) {
            txtOverview.setText(movieModel.getOverview());
        } else {
            txtOverview.setText(getResources().getString(R.string.str_null_overview));
        }

        if (movieModel.getRuntime()!=0) {
            TextView txtRuntime = findViewById(R.id.txt_runtime);
            txtRuntime.setText(String.format("%s: %d %s", getResources().getString(R.string.str_duration), movieModel.getRuntime(), getResources().getString(R.string.str_min)));
        }

        TextView txtStatus = findViewById(R.id.txt_status);
        if (movieModel.getRelease_date().length() > 3) {
            txtStatus.setText(movieModel.getRelease_date().substring(0, 4) + " - " + movieModel.getStatus());
        }

        ImageView imgPoster = findViewById(R.id.img_poster);
        Picasso.get().load(IMG_BASE_URL + "w500/" + movieModel.getPoster_path()).into(imgPoster);

        ImageView imgBackdrop = findViewById(R.id.img_backdrop);
        Picasso.get().load(IMG_BASE_URL + "w1280/" + movieModel.getBackdrop_path()).into(imgBackdrop);
    }

    public void implementFabFunctionality() {
        // register the extended floating action Button
        ExtendedFloatingActionButton extendedFloatingActionButton = findViewById(R.id.extended_fab);

        // register the nestedScrollView from the main layout
        NestedScrollView nestedScrollView = findViewById(R.id.nested_scroll_view);

        // handle the nestedScrollView behaviour with OnScrollChangeListener
        // to extend or shrink the Extended Floating Action Button
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // the delay of the extension of the FAB is set for 12 items
                if (scrollY > oldScrollY + 12 && extendedFloatingActionButton.isExtended()) {
                    extendedFloatingActionButton.shrink();
                }

                // the delay of the extension of the FAB is set for 12 items
                if (scrollY < oldScrollY - 12 && !extendedFloatingActionButton.isExtended()) {
                    extendedFloatingActionButton.extend();
                }

                // if the nestedScrollView is at the first item of the list then the
                // extended floating action should be in extended state
                if (scrollY == 0) {
                    extendedFloatingActionButton.extend();
                }
            }
        });


        Dao dao = new Dao();


        dao.getUserReference().child("MovIeTs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(String.valueOf(movieID)).exists()) {
                    extendedFloatingActionButton.setIconResource(R.drawable.ic_baseline_check);
                    extendedFloatingActionButton.setText(R.string.str_added);
                    added = true;
                }
                else {
                    extendedFloatingActionButton.setIconResource(R.drawable.ic_baseline_library_add_24);
                    extendedFloatingActionButton.setText(R.string.app_name);
                    added = false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

            // Add movie to MovIeT List
        extendedFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!added) {
                    // Add movie
                    dao.addMoviet(movieModel, getApplicationContext())
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.error_default), Toast.LENGTH_LONG).show();
                                }
                            });
                }
                else {
                    // Remove movie
                    dao.removeMoviet(String.valueOf(movieID))
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.error_default), Toast.LENGTH_LONG).show();
                                }
                            });
                }
            }
        });
    }



}