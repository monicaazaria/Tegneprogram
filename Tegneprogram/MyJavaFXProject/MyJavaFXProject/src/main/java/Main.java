package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tegneprogram");

        // Opprett hovedlayout
        BorderPane mainLayout = new BorderPane();

        // Opprett tegneflaten
        DrawingPane drawingPane = new DrawingPane();

        // Opprett ComboBox for å velge figurtype
        ComboBox<String> shapeSelector = new ComboBox<>();
        shapeSelector.getItems().addAll("Sirkel", "Linje", "Rektangel");

        // Opprett fargevelgere for linjefarge og fyllfarge
        ColorPicker lineColorPicker = new ColorPicker();
        ColorPicker fillColorPicker = new ColorPicker();

        // Når en figurtype velges, oppdater tegnepanelet
        shapeSelector.setOnAction(event -> {
            drawingPane.setSelectedShape(shapeSelector.getValue());
        });

        // Når linjefargen endres, oppdater tegnepanelet
        lineColorPicker.setOnAction(event -> {
            drawingPane.setLineColor(lineColorPicker.getValue());
        });

        // Når fyllefargen endres, oppdater tegnepanelet
        fillColorPicker.setOnAction(event -> {
            drawingPane.setFillColor(fillColorPicker.getValue());
        });

        // Legg fargevelgerne til en HBox
        HBox colorPickerBox = new HBox(10, lineColorPicker, fillColorPicker);
        
        // Legg ComboBox og fargevelgere til toppen av layouten
        mainLayout.setTop(shapeSelector);
        mainLayout.setCenter(drawingPane);
        mainLayout.setBottom(colorPickerBox); // Fargevelgere nederst

        // Sett opp scenen
        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
