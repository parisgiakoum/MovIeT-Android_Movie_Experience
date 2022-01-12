package com.regen21.moviet.Authentication;

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
import com.google.firebase.auth.FirebaseUser;
import com.regen21.moviet.R;

public class LoginActivity extends AppCompatActivity {

    private TextView create_newAccount, forgotPassword;
    private EditText editTextEmail, editTextPassword;
    private Button login;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_constraint);

        login = findViewById(R.id.login_linear_login);
        forgotPassword = findViewById(R.id.login_linear_forgot);
        create_newAccount=findViewById(R.id.create_account_linear);
        editTextEmail = findViewById(R.id.login_linear_username_value);
        editTextPassword = findViewById(R.id.login_linear_password_value);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RetrievePasswordActivity.class));
            }
        });

        create_newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


        mAuth = FirebaseAuth.getInstance();
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()) {
            editTextEmail.setError(getString(R.string.email_required));
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.email_invalid));
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            editTextPassword.setError(getString(R.string.password_required));
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6) {
            editTextPassword.setError(getString(R.string.password_length_invalid));
            editTextPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    // Check if e-mail address is verified
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user.isEmailVerified()) {
                        // Redirect to home - TESTING LOGOUT HERE
                        startActivity(new Intent(LoginActivity.this, TestAuthenticationFunctionalities.class));
                    }
                    else {
                        user.sendEmailVerification();
                        Toast.makeText(LoginActivity.this, getString(R.string.email_verification), Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, getString(R.string.login_error), Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}