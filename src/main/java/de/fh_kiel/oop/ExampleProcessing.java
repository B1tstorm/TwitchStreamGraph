package de.fh_kiel.oop;

import processing.core.PApplet;
import processing.core.PImage;

public class ExampleProcessing  extends PApplet {

    PImage mImage;

    public void settings() {
        mImage = loadImage("codefreak.png");
        size(mImage.width, mImage.height);
    }

    public void draw(){
        ellipse(mouseX, mouseY, 50, 50);
    }

    public void mousePressed(){
        image(mImage, 0, 0);
    }

    public static void main(String[] args) {
        String[] processingArgs = {"Grundlegendes Beispiel"};
        ExampleProcessing test = new ExampleProcessing();
        PApplet.runSketch(processingArgs, test);
    }

}
