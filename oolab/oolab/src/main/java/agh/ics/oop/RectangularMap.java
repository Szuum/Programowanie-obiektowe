package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap{
    private final int width;
    private final int height;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        if (!super.canMoveTo(position)) {
            return false;
        }
        return (position.precedes(new Vector2d(width, height)) && position.follows(new Vector2d(0, 0)));
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
