package com.example;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Rektangel {
    private double x, y, width, height;
    private Color lineColor;
    private Color fillColor;

    public Rektangel(double x, double y, double width, double height, Color lineColor, Color fillColor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.lineColor = lineColor;
        this.fillColor = fillColor;
    }

    // Metode for å endre størrelsen på rektangelet
    public void resize(double newWidth, double newHeight) {
        this.width = newWidth;
        this.height = newHeight; // Oppdater dimensjoner
    }

    // Metode for å beregne arealet
    public double getArea() {
        return width * height; // Arealet av et rektangel
    }

    // Metode for å tegne rektangelet
    public void draw(Pane pane) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        rectangle.setStroke(lineColor);
        rectangle.setFill(fillColor); // Bruk fyllfarge her
        pane.getChildren().add(rectangle);
    }

    // Metode for å sjekke om et punkt ligger innenfor rektangelet
    public boolean contains(double x, double y) {
        return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
    }

    // Metode for å oppdatere posisjonen til rektangelet
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Metode for å oppdatere linjefarge
    public void setLineColor(Color color) {
        this.lineColor = color;
    }

    // Metode for å oppdatere fyllfarge
    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    // Gettere for bredde og høyde
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    // Gettere for posisjon og farger
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public Color getFillColor() {
        return fillColor;
    }
}
