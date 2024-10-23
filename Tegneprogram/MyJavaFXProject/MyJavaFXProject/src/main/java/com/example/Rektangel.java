package com.example;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Rektangel extends Figur {
    private double bredde, høyde;

    public Rektangel(double startX, double startY, double bredde, double høyde, Color linjeFarge, Color fyllFarge) {
        super(linjeFarge, fyllFarge);
        this.bredde = bredde;
        this.høyde = høyde;
        this.form = new Rectangle(startX, startY, bredde, høyde);
        this.form.setStroke(linjeFarge);
        this.form.setFill(fyllFarge);
    }

    @Override
    public void tegn() {
        // JavaFX tegner automatisk
    }

    @Override
    public double getAreal() {
        return bredde * høyde;
    }

    @Override
    public double getLengde() {
        return 2 * (bredde + høyde);
    }
}
