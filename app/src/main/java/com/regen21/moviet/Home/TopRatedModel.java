package com.regen21.moviet.Home;

import com.regen21.moviet.Movie.MovieModel;

import java.util.List;

public class TopRatedModel {
    private List<MovieModel> results;
    private int page;

    @Override
    public String toString(){
        return "TopRatedModel{"+
                "results=" + results +
                ", page=" + page +
                "}";
    }

    public List<MovieModel> getTopRated() { return  results; }

    public void setTopRated(List<MovieModel> results){this.results = results;}

    public int getPageTopRated() { return page; }

    public void setPageTopRated(int page){ this.page = page; }

}
