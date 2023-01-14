//package agh.ics.oop.gui; // ten plik nie wydaje się przydatny
//
//import agh.ics.oop.*;
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.geometry.HPos;
//import javafx.geometry.VPos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.*;
//import javafx.stage.Stage;
//
//public class App extends Application implements IPositionChangeObserver {
//
//    GridPane gridPane = new GridPane();
//    AbstractWorldMap map;
//    SimulationEngine engine;
//    Thread engineThread;
//
//    public void start(Stage primaryStage) {
//
//        try {
//
//            MoveDirection[] directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
//            map = new GrassField(10);
//            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4)};
//            SimulationEngine engine = new SimulationEngine(map, positions, this);
//            engine.cnt = engine.addAnimals();
//
//        } catch(IllegalArgumentException exception) {
//            exception.printStackTrace();
//        }
//
//        TextField textField = new TextField();
//        Button start = new Button("Start");
//        start.setOnAction(event -> {
//            String[] text = textField.getText().split(" ");
//            OptionsParser parse = new OptionsParser();
//            MoveDirection[] moves = parse.parse(text);
//            engine.setDiractions(moves);
//            engineThread = new Thread(engine);
//            engineThread.start();
//        });
//        VBox button = new VBox(textField, start);
//        HBox buttonWithMap = new HBox(button, gridPane);
//        this.gridPane.setGridLinesVisible(true);
//        fillMap(map);
//        Scene scene = new Scene(buttonWithMap, 400, 400);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private void drawHeader(AbstractWorldMap map) {
//        Vector2d lowerLeft = map.getLowerLeft();
//        Vector2d upperRight = map.getUpperRight();
//        Label label = new Label("y/x");
//
//        this.gridPane.add(label, 0, 0, 1, 1);
//        GridPane.setHalignment(label, HPos.CENTER);
//
//        for (int i = 1 ; i < upperRight.x - lowerLeft.x + 2 ; i++) {
//            label = new Label(Integer.toString(i - 1));
//            this.gridPane.add(label, i, 0, 1, 1);
//            GridPane.setHalignment(label, HPos.CENTER);
//        }
//
//        for (int i = 1 ; i < upperRight.y - lowerLeft.y + 2 ; i++) {
//            label = new Label(Integer.toString(upperRight.y - lowerLeft.y - i + 1));
//            this.gridPane.add(label, 0, i, 1, 1);
//            GridPane.setHalignment(label, HPos.CENTER);
//        }
//    }
//
//    private void fillMap(AbstractWorldMap map) {
//        drawHeader(map);
//        Vector2d lowerLeft = map.getLowerLeft();
//        Vector2d upperRight = map.getUpperRight();
//
//        for (int x = 1 ; x < upperRight.x - lowerLeft.x + 2 ; x++) {
//            for (int y = 1 ; y < upperRight.y - lowerLeft.y + 2 ; y++) {
//
//                Object object = map.objectAt(new Vector2d(lowerLeft.x + x - 1, upperRight.y - y + 1));
//                Label label = new Label(" ");
//
//                if (object != null) {
//                    GuiElementBox box = new GuiElementBox(object);
//                    label = new Label(object.toString());
//                }
//
//                this.gridPane.add(label, x, y, 1, 1);
//                GridPane.setHalignment(label, HPos.CENTER);
//            }
//        }
//
//        for (int x = 0 ; x < upperRight.x - lowerLeft.x + 2 ; x++) {
//            this.gridPane.getColumnConstraints().add(new ColumnConstraints(25));
//        }
//
//        for (int y = 0 ; y < upperRight.y - lowerLeft.y + 2 ; y++) {
//            this.gridPane.getRowConstraints().add(new RowConstraints(25));
//        }
//    }
//
//    @Override
//    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
//        Platform.runLater(() -> {
//                gridPane.getChildren().clear();
//                fillMap(map);
//        });
//    }
//}
