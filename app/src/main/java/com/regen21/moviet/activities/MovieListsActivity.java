package com.regen21.moviet.activities;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.regen21.moviet.R;
import com.regen21.moviet.activities.authentication.LoginActivity;
import com.regen21.moviet.utils.MenuHandler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MovieListsActivity extends AppCompatActivity {

    // Do nothing on Back btn
    @Override
    public void onBackPressed() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        MenuHandler menuHandler = new MenuHandler(this);

    }
}