package com.example;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Linje extends Figur {
    private double startX, startY, sluttX, sluttY;

    public Linje(double startX, double startY, double sluttX, double sluttY, Color linjeFarge) {
        super(linjeFarge, null);
        this.startX = startX;
        this.startY = startY;
        this.sluttX = sluttX;
        this.sluttY = sluttY;
        this.form = new Line(startX, startY, sluttX, sluttY);
        this.form.setStroke(linjeFarge);
    }

    @Override
    public void tegn() {
        // Ingen handling, JavaFX håndterer tegning
    }

    @Override
    public double getAreal() {
        return 0;  // Linjer har ikke areal
    }

    @Override
    public double getLengde() {
        return Math.sqrt(Math.pow((sluttX - startX), 2) + Math.pow((sluttY - startY), 2));
    }
}
