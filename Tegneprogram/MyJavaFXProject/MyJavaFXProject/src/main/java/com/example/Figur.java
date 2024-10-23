package com.example;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class Figur {
    protected Shape form;
    protected Color linjeFarge;
    protected Color fyllFarge;

    public Figur(Color linjeFarge, Color fyllFarge) {
        this.linjeFarge = linjeFarge;
        this.fyllFarge = fyllFarge;
    }

    public abstract void tegn();
    public abstract double getAreal();
    public abstract double getLengde();

    public void setLinjeFarge(Color farge) {
        this.linjeFarge = farge;
        form.setStroke(farge);
    }

    public void setFyllFarge(Color farge) {
        this.fyllFarge = farge;
        form.setFill(farge);
    }

    public Shape getForm() {
        return form;
    }

    public void flytt(double deltaX, double deltaY) {
        form.setTranslateX(form.getTranslateX() + deltaX);
        form.setTranslateY(form.getTranslateY() + deltaY);
    }
}
