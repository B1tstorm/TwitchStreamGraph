package de.fh_kiel.oop.model;

public interface StreamerInterface {
    String getName();
    String getTitle();
    String getStreamStart();
    Integer getMaxViewers();
    Integer getFirstViewerCount();
    Integer getCurrentViewerCount();
    Integer getViewerElements();
    void setName(String name);
    Boolean isOnline();
}
