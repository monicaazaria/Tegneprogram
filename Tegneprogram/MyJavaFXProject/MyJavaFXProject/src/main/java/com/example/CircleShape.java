package com.example;

import javafx.scene.layout.Pane; 
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleShape {
    private double x, y, radius;
    private Color lineColor;
    private Color fillColor;

    public CircleShape(double x, double y, double radius, Color lineColor, Color fillColor) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.lineColor = lineColor;
        this.fillColor = fillColor;
    }

    public void draw(Pane pane) {
        Circle circle = new Circle(x, y, radius);
        circle.setStroke(lineColor);
        circle.setFill(fillColor);
        pane.getChildren().add(circle);
    }

    // Metode for å beregne areal
    public double getArea() {
        return Math.PI * Math.pow(radius, 2); 
    }
    
    // Metode for å sjekke om et punkt ligger innenfor sirkelen
    public boolean contains(double x, double y) {
        return Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2) <= Math.pow(radius, 2);
    }

    // Metode for å oppdatere radius til sirkelen
    public void resize(double newRadius) {
        this.radius = newRadius; // Oppdater radius
    }
    
    // Metode for å oppdatere sentrum til sirkelen
    public void setCenter(double x, double y) {
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

    // Gettere for å hente posisjon og farger
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
