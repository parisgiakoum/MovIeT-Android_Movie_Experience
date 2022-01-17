package com.regen21.moviet.models;

import com.regen21.moviet.models.MovieModel;

import java.util.List;

public class MovieResultsModel {
    private List<MovieModel> results;
    private int page;

    @Override
    public String toString() {
        return "PopularModel{" +
                "results=" + results +
                ", page=" + page +
                '}';
    }

    public List<MovieModel> getResults() {
        return results;
    }

    public void setResults(List<MovieModel> results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

}


