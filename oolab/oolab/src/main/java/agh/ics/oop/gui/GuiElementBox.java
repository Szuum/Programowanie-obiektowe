package agh.ics.oop.gui;

import java.io.FileInputStream;
import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class GuiElementBox {

    public GuiElementBox(IMapElement element) {

        Image image = new Image(new FileInputStream(element.getImagePath()));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        Label label = new Label();
        if (element instanceof Animal) {
            label = new Label("Z" + element.getPosition().toString());
        }
        else {
            label = new Label("Trawa");
        }

        VBox vbox = new VBox(imageView, label);
        vbox.setAlignment(Pos.CENTER);
    }
}
