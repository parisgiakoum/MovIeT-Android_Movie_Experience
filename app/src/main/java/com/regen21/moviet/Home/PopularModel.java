package com.regen21.moviet.Home;

import com.regen21.moviet.Movie.MovieActivity;
import com.regen21.moviet.Movie.MovieModel;

import java.util.List;

public class PopularModel {
    private List<MovieModel> results;
    private int page;

    @Override
    public String toString() {
        return "PopularModel{" +
                "results=" + results +
                ", page=" + page +
                '}';
    }

    public List<MovieModel> getPopular() {
        return results;
    }

    public void setPopular(List<MovieModel> results) {
        this.results = results;
    }

    public int getPagePopular() {
        return page;
    }

    public void setPagePopular(int page) {
        this.page = page;
    }
}
