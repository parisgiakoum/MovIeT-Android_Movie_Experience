package com.regen21.moviet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        TextView username = findViewById(R.id.usernameText);
        TextView movies_num = findViewById(R.id.moviesWatchedText);
        UserProfile profile = new UserProfile();
        profile.setUserData("kokobios",10);

        username.setText("Hello, "+profile.getUsername());
        movies_num.setText("You have watched "+profile.getMoviesWatched()+" movies");

    }
}