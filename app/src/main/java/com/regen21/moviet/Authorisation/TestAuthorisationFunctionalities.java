package com.regen21.moviet.Authorisation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.regen21.moviet.Home.HomeActivity;
import com.regen21.moviet.R;

// THIS IS A TEST ONLY CLASS
public class TestAuthorisationFunctionalities extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_logout_btn);

        Button logoutBtn = findViewById(R.id.logout_btn);
        Button homeBtn = findViewById(R.id.home_btn);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(TestAuthorisationFunctionalities.this, LoginActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestAuthorisationFunctionalities.this, HomeActivity.class));
            }
        });
    }
}