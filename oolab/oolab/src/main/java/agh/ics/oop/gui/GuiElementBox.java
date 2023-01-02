package agh.ics.oop.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class GuiElementBox {

    protected VBox vbox;

    public GuiElementBox(IMapElement element) {
        try {
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected VBox getVBox() {
        return this.vbox;
    }
}
