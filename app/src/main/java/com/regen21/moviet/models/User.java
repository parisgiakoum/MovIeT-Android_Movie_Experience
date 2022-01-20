package com.regen21.moviet.models;

import java.util.List;

public class User {

    private String username;
    private String email;
    private List<MovieModel> movies;

    public User() {

    }

    public User(String username, String email, List<MovieModel> movies) {
        this.username = username;
        this.email = email;
        this.movies = movies;
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<MovieModel> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieModel> movies) {
        this.movies = movies;
    }
}
