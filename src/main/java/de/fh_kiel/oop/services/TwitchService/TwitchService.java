package de.fh_kiel.oop.services.TwitchService;

import de.fh_kiel.oop.services.ObserverPattern.Subject;
import de.fh_kiel.oop.model.StreamInfos;

public class TwitchService extends Subject {
    private final StreamInfos stream;
    private final TwitchApiRequestInterface twichRequest;
    private Integer viewers;

    public TwitchService(String StreamerName) {
        String twichUrl = "https://api.twitch.tv/helix/streams";
        this.twichRequest = new TwitchTwitchApiRequestInterface(twichUrl);
        this.stream = new StreamInfos(StreamerName);
    }

    /**
     * Wird periodisch in Application aufgerufen
     */
    public void updateStreamData() {
        String apiResponse = twichRequest.getStreams(stream.getName());
        TwitchApiResponseInterface twitchResponse = new TwitchApiResponse(apiResponse);

        if (twitchResponse.isAnyData()) {
            setStreamData(true, twitchResponse.getTitle(), twitchResponse.getStartedAt(), twitchResponse.getViewerCount());
        } else {
            setStreamData(false, "Offline!", "00:00:00", 0);
        }

        // überprüfen ob Anzahl der viewer sich geändert hat, wenn ja subscriber benachrichtigt
        if (this.viewers == null || !twitchResponse.getViewerCount().equals(this.viewers)) {
            System.err.println("state update");
            this.setState(stream);
            this.viewers = twitchResponse.getViewerCount();
        }
    }

    private void setStreamData(boolean online, String title, String startTime, Integer viewer) {
        stream.setOnlineState(online);
        stream.setTitle(title);
        stream.setStreamStart(startTime);
        stream.addViewerCount(viewer);
    }
}
