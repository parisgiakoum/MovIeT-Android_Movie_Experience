package com.regen21.moviet.models.creditModels;

public class CastModel {

    private int id;
    private String name;
    private String profile_path;
    private String character;

    @Override
    public String toString() {
        return "CastModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profile_path='" + profile_path + '\'' +
                ", character='" + character + '\'' +
                '}';
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
