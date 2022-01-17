package com.regen21.moviet.Movie;

import android.content.Context;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.regen21.moviet.R;

import java.util.List;

public class MovieModel {

    private Context context;
    private int id;
    private String title;
    private String release_date;
    @Nullable
    private String overview;
    @Nullable
    private String poster_path;
    @Nullable
    private int runtime;
    private String status;
    private float vote_average;
    @Nullable
    private String tagline;
    private List<GenresModel> genres;
    @Nullable
    private String backdrop_path;
    private int vote_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        if (release_date !=null) {
            return release_date;
        }
        else {
            return "";
        }
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        if (overview!=null) {
            return overview;
        } else {
            return "";
        }
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        if (poster_path != null) {
            return poster_path;
        } else {
            return "";
        }
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getTagline() {
        if (tagline != null) {
            return tagline;
        } else {
            return "";
        }
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public List<GenresModel> getGenres() {
        return genres;
    }

    public void setGenres(List<GenresModel> genres) {
        this.genres = genres;
    }

    public String getBackdrop_path() {
        if (backdrop_path != null) {
            return backdrop_path;
        } else {
            return "";
        }
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    @Override
    public String toString() {
        return "MovieModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", release_date='" + release_date + '\'' +
                ", overview='" + overview + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", runtime=" + runtime +
                ", status='" + status + '\'' +
                ", vote_average=" + vote_average +
                ", tagline='" + tagline + '\'' +
                ", genres=" + genres +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", vote_count=" + vote_count +
                '}';
    }
}
