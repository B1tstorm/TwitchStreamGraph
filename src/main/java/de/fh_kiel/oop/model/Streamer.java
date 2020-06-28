package de.fh_kiel.oop.model;

public abstract class Streamer implements StreamerInterface {
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
