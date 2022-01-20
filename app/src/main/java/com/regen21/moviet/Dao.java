package com.regen21.moviet;

import android.content.Context;
import android.os.AsyncTask;
import android.view.ViewParent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.regen21.moviet.models.MovieModel;
import com.regen21.moviet.models.User;

public class Dao {
    private DatabaseReference userReference;

    public DatabaseReference getUserReference() {
        return userReference;
    }

    public Dao() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        String userID  = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userReference = db.getReference("Users").child(userID);
    }

    public Task<Void> addMoviet(MovieModel movie, Context context) {
        return userReference.child("MovIeTs").child(String.valueOf(movie.getId())).setValue(movie);
    }

    public Task<Void> removeMoviet(String movieId) {
        return userReference.child("MovIeTs").child(movieId).removeValue();
    }

//    public boolean isInMoviet(String movieId) {
//        userReference.child("MovIeTs").child(movieId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                return snapshot.child("MovIeT").child(movieId).exists();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        })
//    }


}
