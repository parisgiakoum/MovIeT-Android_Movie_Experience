package com.regen21.moviet.models.creditModels;

import java.util.List;

public class CreditsModel {
    private List<CastModel> cast;
    private List<CrewModel> crew;
    private int id;

    @Override
    public String toString() {
        return "CreditsModel{" +
                "cast=" + cast +
                ", crew=" + crew +
                ", id=" + id +
                '}';
    }

    public List<CrewModel> getCrew() {
        return crew;
    }

    public void setCrew(List<CrewModel> crew) {
        this.crew = crew;
    }

    public List<CastModel> getCast() {
        return cast;
    }

    public void setCast(List<CastModel> cast) {
        this.cast = cast;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

