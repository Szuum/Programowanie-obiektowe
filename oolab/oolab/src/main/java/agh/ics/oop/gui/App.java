package agh.ics.oop.gui;

import javafx.application.Application;
import agh.ics.oop.*;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {

    GridPane gridPane = new GridPane();

    public void start(Stage primaryStage) {

        try {

            MoveDirection[] directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
            AbstractWorldMap map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
            System.out.println(map.toString());
            fillMap(map);

        } catch(IllegalArgumentException exception) {
            System.out.println(exception);
        }

        this.gridPane.setGridLinesVisible(true);
        Scene scene = new Scene(this.gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawHeader(AbstractWorldMap map) {
        Vector2d lowerLeft = map.getLowerLeft();
        Vector2d upperRight = map.getUpperRight();
        Label label = new Label("y/x");

        this.gridPane.add(label, 0, 0, 1, 1);
        GridPane.setHalignment(label, HPos.CENTER);

        for (int i = 1 ; i < upperRight.x - lowerLeft.x + 2 ; i++) {
            label = new Label(Integer.toString(i));
            this.gridPane.add(label, i, 0, 1, 1);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int i = 1 ; i < upperRight.y - lowerLeft.y + 2 ; i++) {
            label = new Label(Integer.toString(upperRight.y - lowerLeft.y - i));
            this.gridPane.add(label, 0, i, 1, 1);
            GridPane.setHalignment(label, HPos.CENTER);
        }
    }

    private void fillMap(AbstractWorldMap map) {
        drawHeader(map);
        Vector2d lowerLeft = map.getLowerLeft();
        Vector2d upperRight = map.getUpperRight();

        for (int x = 1 ; x < upperRight.x - lowerLeft.x + 2 ; x++) {
            for (int y = 1 ; y < upperRight.y - lowerLeft.y + 2 ; y++) {

                Object object = map.objectAt(new Vector2d(lowerLeft.x + x - 1, upperRight.y - y + 1));
                Label label = new Label(" ");

                if (object != null) {
                    label = new Label(object.toString());
                }

                this.gridPane.add(label, x, y, 1, 1);
                GridPane.setHalignment(label, HPos.CENTER);
            }
        }

        for (int x = 0 ; x < upperRight.x - lowerLeft.x + 2 ; x++) {
            this.gridPane.getColumnConstraints().add(new ColumnConstraints(25));
        }

        for (int y = 0 ; y < upperRight.y - lowerLeft.y + 2 ; y++) {
            this.gridPane.getRowConstraints().add(new RowConstraints(25));
        }
    }

}
