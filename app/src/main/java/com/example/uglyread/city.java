package com.example.uglyread;

public class city {
    private String name;
    private int current,acquired,death,cured;
    public city(String name,int current,int acquired,int death,int cured){
        this.name=name;
        this.current=current;
        this.acquired=acquired;
        this.death=death;
        this.cured=cured;
    }

    public String getName() {
        return name;
    }

    public int getCurrent() {
        return current;
    }

    public int getAcquired() {
        return acquired;
    }

    public int getDeath() {
        return death;
    }

    public int getCured() {
        return cured;
    }
}
