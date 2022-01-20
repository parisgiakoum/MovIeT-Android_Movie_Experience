package com.regen21.moviet.activities;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.regen21.moviet.models.User;
import com.regen21.moviet.R;
import com.regen21.moviet.utils.MenuHandler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        MenuHandler menuHandler = new MenuHandler(this);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        TextView textViewUsername, textViewEmail;
        textViewUsername = findViewById(R.id.username);
        textViewEmail = findViewById(R.id.email);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    String username = userProfile.getUsername();
                    String email = userProfile.getEmail();

                    textViewUsername.setText(username);
                    textViewEmail.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserActivity.this, getString(R.string.error_default), Toast.LENGTH_LONG).show();
            }
        });

        // CHANGE USERNAME
        ImageButton imageButtonEdit = findViewById(R.id.edit_username_icon);
        imageButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child(userID).child("username").setValue("paris").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(UserActivity.this, "Username changed successfully!", Toast.LENGTH_LONG).show();

                        // see changes from database
                        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                User userProfile = snapshot.getValue(User.class);

                                if (userProfile != null) {
                                    String username = userProfile.getUsername();
                                    String email = userProfile.getEmail();

                                    textViewUsername.setText(username);
                                    textViewEmail.setText(email);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(UserActivity.this, getString(R.string.error_default), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UserActivity.this, getString(R.string.error_default), Toast.LENGTH_LONG).show();
                    }
                });;
            }
        });
    }
}
