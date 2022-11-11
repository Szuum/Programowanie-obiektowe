package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class GrassField extends AbstractWorldMap {

    public int number;
    protected List<Grass> grasses = new ArrayList<>();

    public GrassField(int number) {
        this.number = number;
        int maxPosition = (int) Math.sqrt(this.number * 10);
        int i = 0;
        while (i < number) {
            Vector2d position = new Vector2d((int) (Math.random() * maxPosition), (int) (Math.random() * maxPosition));
            if (!grassOnPlace(position)) {
                grasses.add(new Grass(position));
                i++;
            }
        }
    }

    private boolean grassOnPlace(Vector2d position) {
        for (int i = 0 ; i < grasses.size() ; i++) {
            if (position.equals(grasses.get(i).getPosition())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (animals.contains(objectAt(position))) {
            return false;
        }
        if (position.precedes(new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE)) && position.follows(new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE))) {
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

        for (int i = 0; i < grasses.size(); i++) {
            Grass grass = grasses.get(i);
            if (position.equals(grass.getPosition())) {
                return grass;
            }
        }
        return null;
    }

    protected Vector2d getLowerLeft() {
        Vector2d lowerLeft = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (int i = 0 ; i < animals.size() ; i++) {
            lowerLeft = lowerLeft.lowerLeft(animals.get(i).getPosition());
        }
        for (int i = 0 ; i < number ; i++) {
            lowerLeft = lowerLeft.lowerLeft(grasses.get(i).getPosition());
        }
        return lowerLeft;
    }

    protected Vector2d getUpperRight() {
        Vector2d upperRight = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (int i = 0 ; i < animals.size() ; i++) {
            upperRight = upperRight.upperRight(animals.get(i).getPosition());
        }
        for (int i = 0 ; i < number ; i++) {
            upperRight = upperRight.upperRight(grasses.get(i).getPosition());
        }
        return upperRight;
    }
}
