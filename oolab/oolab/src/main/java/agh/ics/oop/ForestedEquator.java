package agh.ics.oop;

import java.util.LinkedList;

public class ForestedEquator implements IGrowingGrass {

    LinkedList<Vector2d> equator = new LinkedList<>();
    LinkedList<Vector2d> steppe = new LinkedList<>();
    private final int width;
    private final int height;
    private final int steppeHeight;

    public ForestedEquator(int width, int height) {
        this.width = width;
        this.height = height;
        this.steppeHeight = (int) (0.1*height);
        addField();
    }

    protected void addField() {
        for (int i = 0 ; i < width ; i++) {
            for (int j = 0 ; j < steppeHeight ; j++) {
                steppe.add(new Vector2d(i, j));
            }
            for (int j = steppeHeight ; j < height - steppeHeight ; j++) {
                equator.add(new Vector2d(i, j));
            }
            for (int j = height - steppeHeight ; j < height ; j++) {
                steppe.add(new Vector2d(i, j));
            }
        }
    }

    private Vector2d grownOnStep() {
        int idx = (int) (Math.random()*(steppe.size() + 1));
        Vector2d position = steppe.get(idx);
        steppe.remove(idx);
        return position;
    }

    private Vector2d grownOnEquator() {
        int idx = (int) (Math.random()*(equator.size() + 1));
        Vector2d position = equator.get(idx);
        equator.remove(idx);
        return position;
    }

    @Override
    public Vector2d grownGrass() {
        int type = (int) (Math.random()*10);
        if (type < 8) {
            return grownOnEquator();
        }
        else {
            return grownOnStep();
        }
    }

    @Override
    public void deleteGrass(Vector2d position) {
        if (position.y < steppeHeight || position.y >= height - steppeHeight) {
            steppe.add(position);
        }
        else {
            equator.add(position);
        }
    }
}
