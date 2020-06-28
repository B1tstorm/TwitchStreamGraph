package de.fh_kiel.oop.services.PlotService;

import grafica.GPlot;
import grafica.GPointsArray;
import processing.core.PApplet;

/**
 * Sinlgeton (Anti)Muster
 */
public class PlotService {
    private final GPlot plot;
    private final GPointsArray points;
    private static PlotService instance = null;
    private PlotService(PApplet pApplet) {
        this.plot = new GPlot(pApplet);
        this.points = new GPointsArray(0);
    }

    public static PlotService getInstance(PApplet pApplet) {
        if (PlotService.instance == null) {
            instance = new PlotService(pApplet);
        }

        return instance;
    }

    public void draw(int xCoordinate, int yCoordinate, String title, String xAxisLabel, String yAxisLabel) {
        plot.setPos(80, 40);
        plot.setBgColor(51);
        plot.setBoxBgColor(51);
        plot.setBoxLineColor(51);
        // Set the plot title and the axis labels
        plot.setTitleText(title);
        plot.getXAxis().setAxisLabelText(xAxisLabel);
        plot.getYAxis().setAxisLabelText(yAxisLabel);

        points.add(xCoordinate, yCoordinate);

        // Add the points
        plot.setPoints(points);

        // Draw it!
        plot.defaultDraw();
    }

    public int getNPoints() {
        return points.getNPoints();
    }
}
