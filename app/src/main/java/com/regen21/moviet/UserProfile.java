package com.regen21.moviet;

public class UserProfile {

    private String username;
    private int movies_watched;

    public String getUsername() {
        return username;
    }

    public int getMoviesWatched() {
        return movies_watched;
    }

    public void setUserData(String input_username, int input_movies) {
        username = input_username;
        movies_watched = input_movies;
    }

}
