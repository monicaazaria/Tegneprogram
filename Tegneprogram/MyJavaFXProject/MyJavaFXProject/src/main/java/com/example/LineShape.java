package com.example;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LineShape {
    private double startX, startY, endX, endY;
    private Color lineColor;

    public LineShape(double startX, double startY, double endX, double endY, Color lineColor) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.lineColor = lineColor;
    }

    public void draw(Pane pane) {
        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(lineColor);
        pane.getChildren().add(line);
    }

    // Setter for endepunkt
    public void setEnd(double endX, double endY) {
        this.endX = endX;
        this.endY = endY;
    }

    //  linjefarge
    public Color getLineColor() {
        return lineColor;
    }

    // Setter for linjefarge
    public void setLineColor(Color color) {
        this.lineColor = color;
    }
}
