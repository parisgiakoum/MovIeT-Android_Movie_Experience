package com.regen21.moviet;

import java.util.List;

public class CreditsModel {
    private List<CastModel> cast;
    private int id;

    @Override
    public String toString() {
        return "CreditsModel{" +
                "cast=" + cast +
                ", id=" + id +
                '}';
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

