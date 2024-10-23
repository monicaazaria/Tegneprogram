package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        VBox høyrePanel = new VBox();

        Label figurInfoLabel = new Label("Ingen figur valgt");
        ColorPicker linjeFargePicker = new ColorPicker();
        ColorPicker fyllFargePicker = new ColorPicker();

        høyrePanel.getChildren().addAll(figurInfoLabel, new Label("Linje Farge:"), linjeFargePicker, new Label("Fyll Farge:"), fyllFargePicker);
        root.setRight(høyrePanel);

        // Opprett TegnePane og legg det til midten av layouten
        TegnePane tegnPane = new TegnePane(linjeFargePicker, fyllFargePicker, figurInfoLabel);
        root.setCenter(tegnPane);

        // Opprett en scene
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Tegneprogram");
        stage.setScene(scene);
        stage.show();

        // Legg til en linje som eksempel
        Linje linje = new Linje(100, 100, 300, 300, Color.BLACK);
        tegnPane.leggTilFigur(linje);

        // Legg til en rektangel som eksempel
        Rektangel rektangel = new Rektangel(400, 200, 100, 80, Color.BLUE, Color.LIGHTBLUE);
        tegnPane.leggTilFigur(rektangel);

        // Legg til en sirkel som eksempel
        Sirkel sirkel = new Sirkel(200, 200, 50, Color.RED, Color.PINK);
        tegnPane.leggTilFigur(sirkel);

        // Endre farge på valgt figur
        linjeFargePicker.setOnAction(e -> tegnPane.oppdaterLinjeFarge(linjeFargePicker.getValue()));
        fyllFargePicker.setOnAction(e -> tegnPane.oppdaterFyllFarge(fyllFargePicker.getValue()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
