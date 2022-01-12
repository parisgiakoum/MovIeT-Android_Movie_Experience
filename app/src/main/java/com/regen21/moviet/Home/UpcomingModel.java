package com.regen21.moviet.Home;

import com.regen21.moviet.Movie.MovieModel;

import java.util.List;

public class UpcomingModel {

    private List<MovieModel> results;
    private int page;

    @Override
    public String toString() {
        return "UpcomingModel{" +
                "results=" + results +
                ", page=" + page +
                '}';
    }

    public List<MovieModel> getUpcoming() {
        return results;
    }

    public void setUpcoming(List<MovieModel> results) {
        this.results = results;
    }

    public int getPageUpcoming() {
        return page;
    }

    public void setPageUpcoming(int page) {
        this.page = page;
    }
}
