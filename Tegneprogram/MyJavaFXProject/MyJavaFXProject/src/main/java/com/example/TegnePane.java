package com.example;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class TegnePane extends Pane {
    private List<Figur> figurer = new ArrayList<>();
    private Figur valgtFigur = null;
    private ColorPicker linjeFargePicker;
    private ColorPicker fyllFargePicker;
    private Label figurInfoLabel;

    public TegnePane(ColorPicker linjeFargePicker, ColorPicker fyllFargePicker, Label figurInfoLabel) {
        this.linjeFargePicker = linjeFargePicker;
        this.fyllFargePicker = fyllFargePicker;
        this.figurInfoLabel = figurInfoLabel;

        // Set up event handling for selecting and moving figures
        setOnMouseClicked(this::håndterKlikk);
        setOnMouseDragged(this::håndterDra);
    }

    public void leggTilFigur(Figur figur) {
        figurer.add(figur);
        getChildren().add(figur.getForm());
    }

    private void håndterKlikk(MouseEvent event) {
        for (Figur figur : figurer) {
            if (figur.getForm().contains(event.getX(), event.getY())) {
                valgtFigur = figur;
                oppdaterFigurInfo();
                linjeFargePicker.setValue(figur.linjeFarge);
                fyllFargePicker.setValue(figur.fyllFarge);
                break;
            }
        }
    }

    private void oppdaterFigurInfo() {
        figurInfoLabel.setText("Valgt figur: " + valgtFigur.getClass().getSimpleName() +
                "\nAreal: " + valgtFigur.getAreal() + " kvadratpixler" +
                "\nLengde: " + valgtFigur.getLengde() + " pixler");
    }

    private void håndterDra(MouseEvent event) {
        if (valgtFigur != null) {
            valgtFigur.flytt(event.getX() - valgtFigur.getForm().getTranslateX(), event.getY() - valgtFigur.getForm().getTranslateY());
        }
    }

    // Endre farge på valgt figur
    public void oppdaterLinjeFarge(Color nyFarge) {
        if (valgtFigur != null) {
            valgtFigur.setLinjeFarge(nyFarge);
        }
    }

    public void oppdaterFyllFarge(Color nyFarge) {
        if (valgtFigur != null) {
            valgtFigur.setFyllFarge(nyFarge);
        }
    }
}
