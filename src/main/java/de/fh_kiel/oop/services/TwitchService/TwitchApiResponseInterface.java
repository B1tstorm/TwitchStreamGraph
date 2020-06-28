package de.fh_kiel.oop.services.TwitchService;

public interface TwitchApiResponseInterface {
    boolean isAnyData();

    Integer getViewerCount();

    String getTitle();

    String getStartedAt();
}
