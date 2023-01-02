package Projekt;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class App extends Application {
    int width = 10;
    int height = 10;
    int startGrass = 20;
    int plusEnergy = 8;
    int grownGrass = 7;
    AbstractMap mapVariant = new HellPortal(width, height);
    int startAnimals = 15;
    int startEnergy = 30;
    int energyToMultiplication = 20;
    int lostEnergy = 4;
    int minMutation = 0;
    int maxMutation = 1;
    IMutation mutationVariant = new SlightCorrection();
    int genomSize = 6;
    NormalOrder nextGenVariant = new NormalOrder();
    IGrowVariant growVariant = new ToxicCorpses(width, height, plusEnergy);

    protected int animalCnt = 0;
    protected int grassCnt = 0;
    protected int freeFieldCnt = width*height;
    protected int[] mostPopularGenom = new int[genomSize];
    protected int totalEnergy = 0;
    protected int deadAnimal = 0;
    protected int totalLife = 0;

    Simulation engine;
    GridPane gridPane;
    VBox stats;
    HBox statsAndMap;
    Button stopAnimation;
    Thread engineThread;

    @Override
    public void init() {
        try {
            engine = new Simulation(width, height, startGrass, plusEnergy, grownGrass, mapVariant, startAnimals, startEnergy, energyToMultiplication,
                    lostEnergy, minMutation, maxMutation, mutationVariant, genomSize, nextGenVariant, this, growVariant);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void start(Stage primaryStage) {
        gridPane = new GridPane();
        stats = new VBox();
        gridPane.setGridLinesVisible(true);
        engineThread = new Thread(engine);
        updateScene();
        statsAndMap = new HBox(stats, gridPane);
        Scene scene = new Scene(statsAndMap);
        primaryStage.setScene(scene);
        primaryStage.show();
        engineThread.start();
    }

    protected void updateScene() {
        freeFieldCnt = width*height;
        for (int x = 0 ; x < width ; x++) {
            for (int y = height - 1 ; y >= 0 ; y--) {
                IMapElement element = engine.objectAt(new Vector2d(x, y));
                if (element != null) {
                    try {
                        Image image = new Image(new FileInputStream(element.getImagePath()));
                        ImageView imageView = new ImageView(image);
                        Label label = new Label(element.getData());
                        imageView.setFitWidth(20);
                        imageView.setFitHeight(20);
                        VBox mapElement = new VBox(imageView, label);
                        gridPane.add(mapElement, x, height - y - 1, 1, 1);
                        GridPane.setHalignment(label, HPos.CENTER);
                        GridPane.setHalignment(imageView, HPos.CENTER);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        for (int i = 0; i < width; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(40));
        }
        for (int i = 0; i < height; i++){
            gridPane.getRowConstraints().add(new RowConstraints(40));
        }

        Label sumOfAnimals = new Label("Numbber of animals: " + Integer.toString(animalCnt));
        Label sumOfGrasses = new Label("Number of grasses: " + Integer.toString(grassCnt));
        Label sumOfFreeFields = new Label("Free places: " + Integer.toString(freeFieldCnt));
        Label popularGenom = new Label("Most popular genom: " + Arrays.toString(mostPopularGenom));
        Label AVGLife = new Label("AVG life: " + updateAVGLife());
        Label AVGEnergy = new Label("AVG energy: " + updateAVGEnergy());
        stats.getChildren().add(sumOfAnimals);
        stats.getChildren().add(sumOfGrasses);
        stats.getChildren().add(sumOfFreeFields);
        stats.getChildren().add(popularGenom);
        stats.getChildren().add(AVGEnergy);
        stats.getChildren().add(AVGLife);
    }

    private String updateAVGLife() {
        if (deadAnimal == 0) {
            return "-";
        }
        return Double.toString(totalLife/deadAnimal);
    }

    private String updateAVGEnergy() {
        if (animalCnt == 0) {
            return "-";
        }
        return Double.toString(totalEnergy/animalCnt);
    }

    protected void mapChanged() {
        Platform.runLater(()->{
            gridPane.getChildren().clear();
            gridPane.getRowConstraints().clear();
            gridPane.getColumnConstraints().clear();
            stats.getChildren().clear();
            this.updateScene();
        });
    }
}
