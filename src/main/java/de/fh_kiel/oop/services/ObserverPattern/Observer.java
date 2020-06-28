package de.fh_kiel.oop.services.ObserverPattern;

import de.fh_kiel.oop.model.StreamerInterface;

public interface Observer {
    void update(StreamerInterface data);
}
