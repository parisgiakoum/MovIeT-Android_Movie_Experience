package com.regen21.moviet;

import android.content.Context;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.regen21.moviet.models.MovieModel;

public class Dao {
    private DatabaseReference userReference;

    public DatabaseReference getUserReference() {
        return userReference;
    }

    public Dao() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            userReference = db.getReference("Users").child(userID);
        }
        else
        {
            userReference = null;
        }
    }

    public Task<Void> addMoviet(MovieModel movie, Context context) {
        return userReference.child("MovIeTs").child(String.valueOf(movie.getId())).setValue(movie);
    }

    public Task<Void> removeMoviet(String movieId) {
        return userReference.child("MovIeTs").child(movieId).removeValue();
    }


}
