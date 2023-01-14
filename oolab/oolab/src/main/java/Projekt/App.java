package Projekt;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class App extends Application {
    int width;
    int height;
    int startGrass;
    int plusEnergy;
    int grownGrass;
    AbstractMap mapVariant;
    int startAnimals;
    int startEnergy;
    int energyToMultiplication;
    int lostEnergy;
    int minMutation;
    int maxMutation;
    IMutation mutationVariant;
    int genomSize;
    NormalOrder nextGenVariant;
    IGrowVariant growVariant;

    protected int animalCnt = 0;
    protected int grassCnt = 0;
    protected int freeFieldCnt;
    protected int[] mostPopularGenom;
    protected int totalEnergy = 0;
    protected int deadAnimal = 0;
    protected int totalLife = 0;

    Simulation engine;
    GridPane gridPane;
    VBox stats;
    HBox statsAndMap;
    Thread engineThread;

    public void start(Stage primaryStage) {
        // stworzenie pól do wpisywania danych (nie wiem jak zrobić odczyt z pliku)
        VBox setData = new VBox();

        Label mapWidth = new Label("map width: ");
        TextField mapWidthTF = new TextField();
        HBox setMapWidth = new HBox(mapWidth, mapWidthTF);
        setData.getChildren().add(setMapWidth);

        Label mapHeight = new Label("map height: ");
        TextField mapHeightTF = new TextField();
        HBox setMapHeight = new HBox(mapHeight, mapHeightTF);
        setData.getChildren().add(setMapHeight);

        Label mapStartGrass = new Label("Number of grass: ");
        TextField mapStartGrassTF = new TextField();
        HBox setStartGrass = new HBox(mapStartGrass, mapStartGrassTF);
        setData.getChildren().add(setStartGrass);

        Label mapEnergyFromGrass = new Label("Energy from grass: ");
        TextField energyFromGrassTF = new TextField();
        HBox setEnergyFromGrass = new HBox(mapEnergyFromGrass, energyFromGrassTF);
        setData.getChildren().add(setEnergyFromGrass);

        Label graasGrwonOnDay = new Label("Grass grown on one day: ");
        TextField grassGrownOnDayTF = new TextField();
        HBox setGrownGrasses = new HBox(graasGrwonOnDay, grassGrownOnDayTF);
        setData.getChildren().add(setGrownGrasses);

        Label mapStartAnimal = new Label("Number of animals: ");
        TextField mapStartAnimalTF = new TextField();
        HBox setStartAnimal = new HBox(mapStartAnimal, mapStartAnimalTF);
        setData.getChildren().add(setStartAnimal);

        Label mapStartEnergy = new Label("Start animal energy: ");
        TextField mapStartEnergyTF = new TextField();
        HBox setStartEnergy = new HBox(mapStartEnergy, mapStartEnergyTF);
        setData.getChildren().add(setStartEnergy);

        Label mapEnergyToMul = new Label("Energy required to multiplication: ");
        TextField mapEnergryToMulTF = new TextField();
        HBox setEnergyToMul = new HBox(mapEnergyToMul, mapEnergryToMulTF);
        setData.getChildren().add(setEnergyToMul);

        Label mapLostEnergy = new Label("Energy lost in multiplication: ");
        TextField mapLostEnergyTF = new TextField();
        HBox setLostEnergy = new HBox(mapLostEnergy, mapLostEnergyTF);
        setData.getChildren().add(setLostEnergy);

        Label mapMinMutation = new Label("Minimum mutation number: ");
        TextField mapMinMutationTF = new TextField();
        HBox setMinMutation = new HBox(mapMinMutation, mapMinMutationTF);
        setData.getChildren().add(setMinMutation);

        Label mapMaxMutation = new Label("Maximum mutation number: ");
        TextField mapMaxMutationTF = new TextField();
        HBox setMaxMutation = new HBox(mapMaxMutation, mapMaxMutationTF);
        setData.getChildren().add(setMaxMutation);

        Label mapGenomSize = new Label("Genom size: ");
        TextField mapGenomSizeTF = new TextField();
        HBox setGenomSize = new HBox(mapGenomSize, mapGenomSizeTF);
        setData.getChildren().add(setGenomSize);

        Label initMapVariant = new Label("Choose map variant: ");
        ChoiceBox chooseMapVariant = new ChoiceBox();
        chooseMapVariant.getItems().add("Hell Portal");
        chooseMapVariant.getItems().add("Globe");
        HBox setMapVariant = new HBox(initMapVariant, chooseMapVariant);
        setData.getChildren().add(setMapVariant);

        Label initGrassVariant = new Label("Choose grass variant: ");
        ChoiceBox chooseGrassVariant = new ChoiceBox();
        chooseGrassVariant.getItems().add("Forested Equator");
        chooseGrassVariant.getItems().add("Toxic Corpses");
        HBox setGrassVariant = new HBox(initGrassVariant, chooseGrassVariant);
        setData.getChildren().add(setGrassVariant);

        Label initMutationVariant = new Label("Choose mutation variant: ");
        ChoiceBox chooseMutationVariant = new ChoiceBox();
        chooseMutationVariant.getItems().add("Random Gen");
        chooseMutationVariant.getItems().add("Slight Correction");
        HBox setMutationVariant = new HBox(initMutationVariant, chooseMutationVariant);
        setData.getChildren().add(setMutationVariant);

        Label initNextGenVariant = new Label("Choose next gen variant: ");
        ChoiceBox chooseNextGenVariant = new ChoiceBox();
        chooseNextGenVariant.getItems().add("Normal Order");
        chooseNextGenVariant.getItems().add("Random Order");
        HBox setNextGenVariant = new HBox(initNextGenVariant, chooseNextGenVariant);
        setData.getChildren().add(setNextGenVariant);

        Button startAnimation = new Button("Start");
        setData.getChildren().add(startAnimation);
        startAnimation.setOnAction(actionEvent -> {
            try { // odczyt danych i zaczęcie animacji
                width = Integer.parseInt(mapWidthTF.getText());
                height = Integer.parseInt(mapHeightTF.getText());
                startGrass = Integer.parseInt(mapStartGrassTF.getText());
                plusEnergy = Integer.parseInt(energyFromGrassTF.getText());
                grownGrass = Integer.parseInt(grassGrownOnDayTF.getText());
                startAnimals = Integer.parseInt(mapStartAnimalTF.getText());
                startEnergy = Integer.parseInt(mapStartEnergyTF.getText());
                energyToMultiplication = Integer.parseInt(mapEnergryToMulTF.getText());
                lostEnergy = Integer.parseInt(mapLostEnergyTF.getText());
                minMutation = Integer.parseInt(mapMinMutationTF.getText());
                maxMutation = Integer.parseInt(mapMaxMutationTF.getText());
                genomSize = Integer.parseInt(mapGenomSizeTF.getText());

                String choosenMapVariant = (String) chooseMapVariant.getValue();
                if (choosenMapVariant == "Hell Portal") {
                    mapVariant = new HellPortal(width, height);
                } else {
                    mapVariant = new Globe(width, height);
                }

                String choosenGrassVariant = (String) chooseGrassVariant.getValue();
                if (choosenGrassVariant == "Forested Equator") {
                    growVariant = new ForestedEquator(width, height, plusEnergy);
                } else {
                    growVariant = new ToxicCorpses(width, height, plusEnergy);
                }

                String choosenMutationVariant = (String) chooseMapVariant.getValue();
                if (choosenMutationVariant == "Ranndom Gen") {
                    mutationVariant = new RandomGen();
                } else {
                    mutationVariant = new SlightCorrection();
                }

                String choosenNextGenVariant = (String) chooseNextGenVariant.getValue();
                if (choosenNextGenVariant == "Normal Order") {
                    nextGenVariant = new NormalOrder();
                } else {
                    nextGenVariant = new RandomOrder();
                }

                engine = new Simulation(width, height, startGrass, plusEnergy, grownGrass, mapVariant, startAnimals, startEnergy, energyToMultiplication,
                        lostEnergy, minMutation, maxMutation, mutationVariant, genomSize, nextGenVariant, this, growVariant);
                animation(primaryStage);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        });

        Scene scene = new Scene(setData);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private void animation(Stage primaryStage) { // animacja
        gridPane = new GridPane();
        stats = new VBox();
        engineThread = new Thread(engine);
        updateScene();
        statsAndMap = new HBox(stats, gridPane);
        Scene scene1 = new Scene(statsAndMap);
        primaryStage.setScene(scene1);
        primaryStage.show();
        engineThread.start();
    }

    protected void updateScene() { // zaktualizowanie mapy i statystyk
        freeFieldCnt = width * height;
        for (int x = 0; x < width; x++) {
            for (int y = height - 1; y >= 0; y--) {
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
        for (int i = 0; i < height; i++) {
            gridPane.getRowConstraints().add(new RowConstraints(40));
        }

        Label sumOfAnimals = new Label("Numbber of animals: " + animalCnt);
        Label sumOfGrasses = new Label("Number of grasses: " + grassCnt);
        Label sumOfFreeFields = new Label("Free places: " + freeFieldCnt);
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
        return Double.toString(totalLife / deadAnimal);
    }

    private String updateAVGEnergy() {
        if (animalCnt == 0) {
            return "-";
        }
        return Double.toString(totalEnergy / animalCnt);
    }

    protected void mapChanged() {
        Platform.runLater(() -> {
            gridPane.getChildren().clear();
            gridPane.getRowConstraints().clear();
            gridPane.getColumnConstraints().clear();
            stats.getChildren().clear();
            this.updateScene();
        });
    }
}