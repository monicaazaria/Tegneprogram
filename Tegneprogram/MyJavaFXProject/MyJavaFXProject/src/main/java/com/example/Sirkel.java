package com.example;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Sirkel extends Figur {
    private double radius;

    public Sirkel(double senterX, double senterY, double radius, Color linjeFarge, Color fyllFarge) {
        super(linjeFarge, fyllFarge);
        this.radius = radius;
        this.form = new Circle(senterX, senterY, radius);
        this.form.setStroke(linjeFarge);
        this.form.setFill(fyllFarge);
    }

    @Override
    public void tegn() {
        // JavaFX tegner automatisk
    }

    @Override
    public double getAreal() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getLengde() {
        return 2 * Math.PI * radius;
    }
}
