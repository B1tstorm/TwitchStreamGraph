package de.fh_kiel.oop;

import de.fh_kiel.oop.services.LogService.LogStatusService;
import de.fh_kiel.oop.services.TwitchService.TwitchService;
import de.fh_kiel.oop.model.StreamerInterface;
import de.fh_kiel.oop.services.PlotService.PlotService;
import processing.core.PApplet;
import processing.core.PFont;
import de.fh_kiel.oop.services.ObserverPattern.*;

import java.util.Scanner;

/**
 * Wird bei TwitchApi als subscriber angemeldet um nur bei Änderungen informiert zu werden
 */
public class Application extends PApplet implements Observer {
    private PFont f;
    private int counter = 0;
    private final TwitchService twitchService;

    private Application(String streamerName) {
        this.twitchService = new TwitchService(streamerName);

        // Application bei TwichService anmelden, bei Änderungen wird die Methode update() aufgerufen, benötigt Observer interface
        twitchService.attach(this);

        // LogStatusService bei TwichService anmelden, bei Änderungen wird die Methode update() aufgerufen, benötigt Observer interface
        twitchService.attach(new LogStatusService());
    }

    @Override
    public void settings() {
        size(650, 720);
    }

    @Override
    public void setup() {
        //PlotService.getInstance(this);
        f = createFont("Arial", 16, true);
    }

    @Override
    public void draw() {
        // Da die Method viele Male pro Sekunden aufgerufen wird, wird abgefrage ob bestimmte Zeit verstrichen ist, bevor neue Daten vom TwitchService abgefragt werden
        if (counter % 360 == 0) {
            // API abfragen, sollte sie der Zustand (die viewers) ändern so wird die Application über update() benachrichtigt
            twitchService.updateStreamData();

            System.out.println("GPlot points: " + PlotService.getInstance(this).getNPoints());

            counter = 0;
        }
        counter++;
    }

    private void display(StreamerInterface data) {
        // Darkmode, muss immer zuerst bildschirm übermalen bevor sich was ändert, sonst wird der text übereinander gelegt
        background(51);
        int windowHeightHalf = height / 2;
        int margin = 100;

        // Titel
        textFont(f, 26);
        fill(200);
        text(data.getName(), (float)margin / 2, windowHeightHalf + 50);
        textFont(f, 18);
        text("Max. Viewer: ", (float)margin / 2, windowHeightHalf + 100);
        text("Start Viewer: ", (float)margin / 2, windowHeightHalf + 150);
        text("Akt. Viewer: ", (float)margin / 2, windowHeightHalf + 200);

        text("Titel: ", (float)margin * 3, (float)windowHeightHalf + 100);
        text("Streamstart.: ", (float)margin * 3, (float)windowHeightHalf + 150);
        text("Live: ", (float)margin * 3, (float)windowHeightHalf + 200);

        // Text
        if (data.isOnline()) {
            fill(0, 255, 0);
        } else {
            fill(255, 0, 0);
        }
        // Max. Viewer
        text(data.getMaxViewers(), (float)margin / 2 + 150, (float)windowHeightHalf + 100);
        // Start Viewer
        text(data.getFirstViewerCount(), (float)margin / 2 + 150, (float)windowHeightHalf + 150);
        // Aktuelle Viewer
        text(data.getCurrentViewerCount(), (float)margin / 2 + 150, (float)windowHeightHalf + 200);
        // Titel
        text(data.getTitle(), (float)margin * 3 + 55, (float)windowHeightHalf + 100);
        // Streamstart
        text(data.getStreamStart(), (float)margin * 3 + 120, (float)windowHeightHalf + 150);
        // Live?
        ellipse((float)margin * 3 + 60, (float)windowHeightHalf + 195, 15, 15);

        // Mittellinie
        stroke(153);
        line(0, (float)height / 2, width, (float)height / 2);
    }

    public static void main(String[] args) {
        // Konsoleneingabe für Streamernamen
        System.out.println("\nNamen des Twitchstreamers eingeben:");
        Scanner scanner = new Scanner(System.in);

        String[] processingArgs = {"Grundlegendes Beispiel"};
        Application test = new Application(scanner.next());
        PApplet.runSketch(processingArgs, test);
    }

    @Override
    public void update(StreamerInterface data) {
        // Daten von TwitchService werden in StreamInfo gespeichert, dieser nutzt StreamerInterface, sobald neue Daten vorhanden werden sie hier übergeben
        display(data);
        PlotService.getInstance(this).draw(data.getViewerElements(), data.getCurrentViewerCount(), data.getTitle(), "Zeit(Minuten)", "Zuschauer");
    }
}
