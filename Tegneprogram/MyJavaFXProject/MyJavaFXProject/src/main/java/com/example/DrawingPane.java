package com.example;

import java.util.ArrayList;
import java.util.Stack;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class DrawingPane extends Pane {
    private ArrayList<CircleShape> circles = new ArrayList<>();
    private ArrayList<LineShape> lines = new ArrayList<>();
    private ArrayList<Rektangel> rectangles = new ArrayList<>();
    private double startX, startY; // Variabler for å lagre startpunkt for linjen
    private Object selectedFigure = null; // For å lagre den valgte figuren
    private LineShape currentLine = null; // For å holde oversikt over linjen som blir tegnet

    private String selectedShape = "Sirkel"; // Standardverdi

    // Stakker for undo/redo
    private Stack<Object> undoStack = new Stack<>();
    private Stack<Object> redoStack = new Stack<>();
    private Label infoLabel; // For å vise informasjon om figuren

    public DrawingPane() {
        this.setOnMousePressed(this::handleMousePressed);
        this.setOnMouseDragged(this::handleMouseDragged);
        this.setOnMouseReleased(this::handleMouseReleased); // Legg til musefrikobling
    }

    public void setSelectedShape(String shape) {
        this.selectedShape = shape;
    }

    public void setInfoLabel(Label label) {
        this.infoLabel = label; // Legg til etiketten som skal vise informasjon
    }

    private void handleMousePressed(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        double radius = 30; // Fast radius for sirkelen
        double rectWidth = 60; // Bredde for rektangelet
        double rectHeight = 40; // Høyde for rektangelet
        Color lineColor = Color.BLACK; // Linjefarge
        Color fillColor = Color.YELLOW; // Fyllfarge

        // Sjekk om brukeren klikker på en eksisterende figur
        for (CircleShape circle : circles) {
            if (circle.contains(x, y)) {
                selectedFigure = circle; // Velg sirkelen
                updateInfoLabel(circle); // Oppdater informasjon
                return;
            }
        }

        for (Rektangel rectangle : rectangles) {
            if (rectangle.contains(x, y)) {
                selectedFigure = rectangle; // Velg rektangelet
                updateInfoLabel(rectangle); // Oppdater informasjon
                return;
            }
        }

        // Hvis ingen figur er valgt, opprett en ny figur
        if (selectedShape.equals("Sirkel")) {
            CircleShape newCircle = new CircleShape(x, y, radius, lineColor, fillColor);
            circles.add(newCircle);
            newCircle.draw(this);
            undoStack.push(newCircle); // Legg til i undo-stakken
        } else if (selectedShape.equals("Rektangel")) {
            Rektangel newRektangel = new Rektangel(x, y, rectWidth, rectHeight, lineColor, fillColor);
            rectangles.add(newRektangel);
            newRektangel.draw(this);
            undoStack.push(newRektangel); // Legg til i undo-stakken
        } else if (selectedShape.equals("Linje")) {
            startX = x;
            startY = y;
            currentLine = new LineShape(startX, startY, startX, startY, lineColor); // Opprett en ny linje
            lines.add(currentLine);
            currentLine.draw(this); // Tegn den med en gang
        }
    }

    private void handleMouseDragged(MouseEvent event) {
        // Flytt den valgte figuren
        if (selectedFigure != null) {
            double x = event.getX();
            double y = event.getY();

            // Flytt figuren til den nye posisjonen
            if (selectedFigure instanceof CircleShape) {
                CircleShape circle = (CircleShape) selectedFigure;
                circle.setCenter(x, y);
            } else if (selectedFigure instanceof Rektangel) {
                Rektangel rectangle = (Rektangel) selectedFigure;
                rectangle.setPosition(x, y);
            }

            // Tegn figuren på nytt etter flytting
            clearAll(); // Tøm panelet
            redraw(); // Tegn figurer på nytt
        } else if (selectedShape.equals("Linje") && currentLine != null) {
            double endX = event.getX();
            double endY = event.getY();

            // Oppdater endepunktet til den nåværende linjen
            currentLine.setEnd(endX, endY); // Implementer setEnd-metoden i LineShape

            // Tegn linjen på nytt
            clearAll(); // Tøm panelet
            redraw(); // Tegn figurer på nytt
            currentLine.draw(this); // Tegn den nåværende linjen
        }
    }

    private void handleMouseReleased(MouseEvent event) {
        if (selectedShape.equals("Linje") && currentLine != null) {
            double endX = event.getX();
            double endY = event.getY();
            currentLine.setEnd(endX, endY); // Fullfør linjen
            undoStack.push(currentLine); // Legg til linjen i undo-stakken
            currentLine = null; // Nullstill aktuell linje
        }
    }

    public void clearAll() {
        this.getChildren().clear(); // Tøm panelet
        circles.clear();
        lines.clear();
        rectangles.clear();
        infoLabel.setText(""); // Tøm informasjon
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Object shape = undoStack.pop();
            if (shape instanceof CircleShape) {
                circles.remove(shape);
            } else if (shape instanceof Rektangel) {
                rectangles.remove(shape);
            }
            clearAll(); // Rens tegneflaten
            redraw(); // Tegn figurer på nytt
        }
    }

    public void redo() {
        // Implementer logikk for redo, hvis du vil
    }

    private void redraw() {
        for (CircleShape circle : circles) {
            circle.draw(this);
        }
        for (Rektangel rectangle : rectangles) {
            rectangle.draw(this);
        }
        for (LineShape line : lines) {
            line.draw(this); // Legg til tegning av linjene
        }
    }

    public void setLineColor(Color color) {
        // Oppdater linjefargen for den valgte figuren
        if (selectedFigure instanceof CircleShape) {
            CircleShape circle = (CircleShape) selectedFigure;
            circle.setLineColor(color);
        } else if (selectedFigure instanceof Rektangel) {
            Rektangel rectangle = (Rektangel) selectedFigure;
            rectangle.setLineColor(color);
        }
    }

    public void setFillColor(Color color) {
        // Oppdater fyllfargen for den valgte figuren
        if (selectedFigure instanceof CircleShape) {
            CircleShape circle = (CircleShape) selectedFigure;
            circle.setFillColor(color);
        } else if (selectedFigure instanceof Rektangel) {
            Rektangel rectangle = (Rektangel) selectedFigure;
            rectangle.setFillColor(color);
        }
    }

    // Oppdater informasjonspanel
    private void updateInfoLabel(CircleShape circle) {
        infoLabel.setText("Areal: " + circle.getArea());
    }

    private void updateInfoLabel(Rektangel rectangle) {
        infoLabel.setText("Areal: " + rectangle.getArea());
    }
}
