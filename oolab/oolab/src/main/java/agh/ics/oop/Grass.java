package agh.ics.oop;

public class Grass implements IMapElement {

    protected Vector2d position;

    public Grass(Vector2d position) {
        this.position = position;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public String toString() {
        return "*";
    }

    @Override
    public String getImagePath() {
        return "C:\\Users\\user\\Documents\\GitHub\\Programowanie-obiektowe\\oolab\\oolab\\src\\main\\resources\\grass.png";
    }


}
