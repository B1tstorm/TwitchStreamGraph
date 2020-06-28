package de.fh_kiel.oop.services.ObserverPattern;

import de.fh_kiel.oop.model.StreamerInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Teil (Subject) von Observer Muster
 * Objekte die etwas anderen anbieten möchten, also Provider sind müssen davon erben.
 * Dadurch lassen sich Konsumer (Subscriber) anmelden und bei Stateänderung benachrichtigen
 */
public class Subject {

    /**
     * Liste mit subscribern
     */
    private final List<Observer> observers = new ArrayList<>();

    /**
     * state setzen und prüfen ob aktueller state ungleich vorheriger, wenn ja dann alle subscriber benachrichtigen
     */
    public void setState(StreamerInterface stream) {
        System.out.println("state different: " );
        notifyAllObservers(stream);
    }

    /**
     * Subscriber hinzufügen
     */
    public void attach(Observer observer){
        observers.add(observer);
    }

    /**
     * Alle subscriber benachrichtigen
     */
    private void notifyAllObservers(StreamerInterface stream) {
        for (Observer observer : observers) {
            observer.update(stream);
        }
    }
}
