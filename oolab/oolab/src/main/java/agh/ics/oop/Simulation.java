package agh.ics.oop;

import javafx.application.Application;
import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Simulation extends Application {
    //    SimulationEngine engine;
    Thread engineThread;
    private int width = 5;
    private int height = 5;
    private AbstractMap mapVariant = new Globe();
    private int startGrass = 5;
    private int plusenergy = 10;
    private int grownGrass = 10;
    private IGrowingGrass grassVariant = new ForestedEquator(width, height);
    private int startAnimals = 3;
    private int startEnergy = 50;
    private int minEnergy = 25;
    private int lostEnergy = 20;
    private int minNumberMutation = 2;
    private int maxNumberMutation = 3;
    private Mutation mutationVariant = new RandomGen();
    private int genomSize = 6;
    private INextGen nextGenVariant = new CrazyChange();
    private GridPane gridPane = new GridPane();
    private Map map;

    public void init() {
        try {
            map = new Map(width, height, plusenergy, lostEnergy, grownGrass, startEnergy, minEnergy, mapVariant, genomSize, nextGenVariant,
                    mutationVariant, grassVariant, minNumberMutation, maxNumberMutation, this);
            engineThread = new Thread(map);
            for (int i = 0; i < startAnimals; i++) {
                int x = (int) (Math.random() * width);
                int y = (int) (Math.random() * height);
                Vector2d position = new Vector2d(x, y);
                Animal animal = new Animal(position, map, startEnergy, genomSize);
                map.place(animal);
            }
            for (int i = 0; i < startGrass; i++) {
                map.addGrass();
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public void start(Stage primaryStage) {
        updateMap();
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
        engineThread.start();
    }

    protected void updateMap() {
        gridPane.getChildren().clear();
        gridPane.getRowConstraints().clear();
        gridPane.getColumnConstraints().clear();
        gridPane.setGridLinesVisible(true);
        for (int x = 0; x < width; x++) {
            for (int y = height - 1; y >= 0; y--) {
                String napis;
                if (map.objectAt(new Vector2d(x, y)) instanceof Animal) {
                    napis = "Z";
                } else if (map.objectAt(new Vector2d(x, y)) instanceof Grass) {
                    napis = "T";
                } else {
                    napis = "";
                }
                Label label = new Label(napis);
                gridPane.add(label, x, y, 1, 1);
                GridPane.setHalignment(label, HPos.CENTER);
            }
        }
        for (int i = 0; i < width; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(20));
        }
        for (int i = 0; i < height; i++) {
            gridPane.getRowConstraints().add(new RowConstraints(20));
        }
    }

    protected void mapChanged() {
        Platform.runLater(() -> updateMap());
    }
}
