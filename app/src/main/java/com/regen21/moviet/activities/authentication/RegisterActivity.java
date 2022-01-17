package com.regen21.moviet.activities.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.regen21.moviet.models.User;
import com.regen21.moviet.R;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView alreadyAcc;
    private Button regBtn, backBtn;
    private EditText editTextUsername, editTextEmail, editTextPassword, editTextVerifyPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        alreadyAcc = findViewById(R.id.already_acc_btn);
        backBtn = findViewById(R.id.register_back_btn);
        regBtn=findViewById(R.id.register_btn);

        editTextUsername = findViewById(R.id.register_username_value);
        editTextEmail = findViewById(R.id.register_email_value);
        editTextPassword = findViewById(R.id.register_password_value);
        editTextVerifyPassword = findViewById(R.id.verify_password_value);

        // Back to login
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        alreadyAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }

    private void registerUser() {
        String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String verifyPassword = editTextVerifyPassword.getText().toString().trim();

        if(username.isEmpty())
        {
            editTextUsername.setError(getString(R.string.username_required));
            editTextUsername.requestFocus();
            return;
        }

        if(email.isEmpty())
        {
            editTextEmail.setError(getString(R.string.email_required));
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.email_invalid));
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            editTextPassword.setError(getString(R.string.password_required));
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6) {
            editTextPassword.setError(getString(R.string.password_length_invalid));
            editTextPassword.requestFocus();
            return;
        }

        if(verifyPassword.isEmpty())
        {
            editTextVerifyPassword.setError(getString(R.string.password_required));
            editTextVerifyPassword.requestFocus();
            return;
        }

        if(!verifyPassword.equals(password)) {
            editTextVerifyPassword.setError(getString(R.string.password_verification_error));
            editTextVerifyPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            User user = new User(username, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, getString(R.string.registration_successful), Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    // Redirect to home
                                    }
                                    else {
                                        Toast.makeText(RegisterActivity.this, getString(R.string.registration_error), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }

                        else {
                            Toast.makeText(RegisterActivity.this, getString(R.string.registration_error), Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }

}
