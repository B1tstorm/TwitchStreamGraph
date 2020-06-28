package de.fh_kiel.oop.model;

import java.util.ArrayList;

public class StreamInfos extends Streamer {
    // Vererbung
    private String title;
    private Boolean online;
    private String streamStart;
    private final ArrayList<Integer> viewers = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStreamStart() {
        return streamStart;
    }

    public void setStreamStart(String streamStart) {
        this.streamStart = streamStart;
    }

    public Boolean isOnline() {
        return online;
    }

    public void setOnlineState(Boolean online) {
        this.online = online;
    }

    public StreamInfos(String name) {
        setName(name);
/*        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println(time.format(formatter));*/
        System.out.println("StreamInfos() wurde erstellt!\n");
    }

    public Integer getMaxViewers() {
        int maxViewers = 0;

        for (Integer viewer : viewers) {
            if (viewer > maxViewers) {
                maxViewers = viewer;
            }
        }

        return maxViewers;
    }

    public Integer getFirstViewerCount() {
        return viewers.get(0);
    }

    public Integer getCurrentViewerCount() {
        return viewers.get(viewers.size() - 1);
    }

    public Integer getViewerElements() {
        return viewers.size();
    }

    public void addViewerCount(Integer viewer) {
        this.viewers.add(viewer);
    }
}
