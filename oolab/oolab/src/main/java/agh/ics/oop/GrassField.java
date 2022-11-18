package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GrassField extends AbstractWorldMap {

    public int number;
    protected HashMap<Vector2d, Grass> grasses = new HashMap<>();

    public GrassField(int number) {
        this.number = number;
        int maxPosition = (int) Math.sqrt(this.number * 10);
        int i = 0;
        while (i < number) {
            Vector2d position = new Vector2d((int) (Math.random() * maxPosition), (int) (Math.random() * maxPosition));
            if (!grassOnPlace(position)) {
                grasses.put(position, new Grass(position));
                i++;
            }
        }
    }

    private boolean grassOnPlace(Vector2d position) {
        return grasses.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (super.objectAt(position) != null) {
            return super.objectAt(position);
        }
        return grasses.get(position);
    }

    protected Vector2d getLowerLeft() {
        Vector2d lowerLeft = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (Vector2d key : animals.keySet()) {
            lowerLeft = lowerLeft.lowerLeft(key);
        }
        for (Vector2d key : grasses.keySet()) {
            lowerLeft = lowerLeft.lowerLeft(key);
        }
        return lowerLeft;
    }

    protected Vector2d getUpperRight() {
        Vector2d upperRight = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (Vector2d key : animals.keySet()) {
            upperRight = upperRight.upperRight(key);
        }
        for (Vector2d key : grasses.keySet()) {
            upperRight = upperRight.upperRight(key);
        }
        return upperRight;
    }
}
