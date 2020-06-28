package de.fh_kiel.oop.services.LogService;

import de.fh_kiel.oop.model.StreamerInterface;
import de.fh_kiel.oop.services.ObserverPattern.Observer;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Wird bei TwitchApi als subscriber angemeldet um nur bei Änderungen informiert zu werden
 */
public class LogStatusService implements Observer {
    @Override
    public void update(StreamerInterface stream) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String date = new java.text.SimpleDateFormat("HH:mm:ss").format(new Date(ts.getTime()));

        System.out.println("Anzahl der viewer hat sich geändert, Zeit: " + date + ", Viewer: " + stream.getCurrentViewerCount());
    }
}
