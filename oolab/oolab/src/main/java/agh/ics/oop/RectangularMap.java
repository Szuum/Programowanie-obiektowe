package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;

public class RectangularMap implements IWorldMap{
    private int width;
    private int height;
    public static List<Animal> animals = new LinkedList<>();

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        if (isOccupied(position)) {
            return false;
        }
        if (position.precedes(new Vector2d(width, height)) && position.follows(new Vector2d(0, 0))) {
            return true;
        }
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (!isOccupied(position)) {
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (objectAt(position) != null) {
            return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            if (position.equals(animal.getPosition())) {
                return animal;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        MapVisualizer draw = new MapVisualizer(this);
        return draw.draw(new Vector2d(0, 0), new Vector2d(width, height));
    }
}
