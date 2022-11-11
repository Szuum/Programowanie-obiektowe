package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public abstract class AbstractWorldMap implements IWorldMap {

    protected List<Animal> animals = new ArrayList<>();

    @Override
    public abstract boolean canMoveTo(Vector2d position);

    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (!animals.contains(objectAt(position))) {
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
    public abstract Object objectAt(Vector2d position);

    protected abstract Vector2d getLowerLeft();

    protected abstract Vector2d getUpperRight();

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(getLowerLeft(), getUpperRight());
    }
}
