package com.regen21.moviet.Authorisation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.regen21.moviet.R;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView alreadyAcc;
    private ImageView backBtn;
    private Button regBtn;
    private EditText editTextUsername, editTextEmail, editTextPassword, editTextVerifyPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        alreadyAcc = findViewById(R.id.already_acc_btn);
        backBtn = findViewById(R.id.register_linear_back_btn);
        regBtn=findViewById(R.id.register_linear_btn);


        editTextUsername = findViewById(R.id.register_linear_username_value);
        editTextEmail = findViewById(R.id.register_linear_email_value);
        editTextPassword = findViewById(R.id.register_linear_password_value);
        editTextVerifyPassword = findViewById(R.id.register2_linear_password_value);

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
            editTextUsername.setError("Username is required");
            editTextUsername.requestFocus();
            return;
        }

        if(email.isEmpty())
        {
            editTextEmail.setError("E-mail is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please provide a valid e-mail address");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6) {
            editTextPassword.setError("Password should be more than 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        if(verifyPassword.isEmpty())
        {
            editTextVerifyPassword.setError("Password verification is required");
            editTextVerifyPassword.requestFocus();
            return;
        }

        if(!verifyPassword.equals(password)) {
            editTextVerifyPassword.setError("Passwords are not the same");
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
                                        Toast.makeText(RegisterActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();

                                        // Redirect to home?
                                    }
                                    else {
                                        Toast.makeText(RegisterActivity.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }

                        else {
                            Toast.makeText(RegisterActivity.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }

}
