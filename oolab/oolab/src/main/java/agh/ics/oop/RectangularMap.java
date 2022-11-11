package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap{
    private int width;
    private int height;

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
    protected Vector2d getLowerLeft() {
        return new Vector2d(0, 0);
    }

    @Override
    protected Vector2d getUpperRight() {
        return new Vector2d(width, height);
    }
}
