package de.fh_kiel.oop.services.TwitchService;

import org.json.JSONArray;
import org.json.JSONObject;

public class TwitchApiResponse implements TwitchApiResponseInterface {
    private final JSONArray data;

    private JSONArray getPropertyFromJSON(String json) {
        JSONObject obj = new JSONObject(json);

        return obj.getJSONArray("data");
    }

    public TwitchApiResponse(String json) {

        this.data = getPropertyFromJSON(json);
    }

    @Override
    public boolean isAnyData() {

        return !this.data.isEmpty();
    }

    @Override
    public Integer getViewerCount() {
        int viewerCount = 0;
        for (int i = 0; i < this.data.length(); i++) {
            viewerCount = this.data.getJSONObject(i).getInt("viewer_count");
        }

        return viewerCount;
    }

    @Override
    public String getTitle() {
        String title = "";
        for (int i = 0; i < this.data.length(); i++) {
            title = this.data.getJSONObject(i).getString("title");
        }

        return title;
    }

    @Override
    public String getStartedAt() {
        String startedAt = "";
        for (int i = 0; i < this.data.length(); i++) {
            startedAt = this.data.getJSONObject(i).getString("started_at");
        }

        return startedAt;
    }
}
