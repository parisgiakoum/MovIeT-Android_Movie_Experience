package com.regen21.moviet.models.creditModels;

public class CrewModel {
    private int id;
    private String name;
    private String profile_path;
    private String job;

    @Override
    public String toString() {
        return "CrewModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profile_path='" + profile_path + '\'' +
                ", job='" + job + '\'' +
                '}';
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
