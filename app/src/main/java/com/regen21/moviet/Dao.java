package com.regen21.moviet;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Dao {

    private DatabaseReference movietsRef, userMovietsRef;
    private String userID;

    public DatabaseReference getMovietsRef() {
        return movietsRef;
    }

    public String getUserID() {return userID;}

    public Dao() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            movietsRef = db.getReference("UserMoviets");
            userMovietsRef = db.getReference("Moviets");
        }
    }

    public void updateMoviet(String movieId, Context context, boolean isMovied) {
        if (!isMovied) {
            movietsRef.child(userID).child(movieId).setValue(movieId).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, context.getString(R.string.error_default), Toast.LENGTH_LONG).show();
                }
            });
            userMovietsRef.child(movieId).child(userID).setValue(userID).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, context.getString(R.string.error_default), Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            movietsRef.child(userID).child(movieId).removeValue().addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, context.getString(R.string.error_default), Toast.LENGTH_LONG).show();
                    }
            });
            userMovietsRef.child(movieId).child(userID).removeValue().addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, context.getString(R.string.error_default), Toast.LENGTH_LONG).show();
                }
            });

        }
    }


}
