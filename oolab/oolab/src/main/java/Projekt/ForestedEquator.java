package Projekt;

import java.util.ArrayList;
import java.util.HashMap;

public class ForestedEquator implements IGrowVariant {
    private final int width;
    private final int height;
    private final int steppeHeight;
    private final int plusEnegry;
    ArrayList<Grass> equator = new ArrayList<>(); // wolne pola na równiku
    ArrayList<Grass> steppes = new ArrayList<>(); // wolne pola na stepie

    public ForestedEquator(int width, int height, int plusEnegry) {
        this.width = width;
        this.height = height;
        this.plusEnegry = plusEnegry;
        steppeHeight = (int) (0.1*height);
        for (int x = 0 ; x < width ; x++) {
            for (int y = 0 ; y < steppeHeight ; y++) {
                Vector2d position = new Vector2d(x, y);
                steppes.add(new Grass(position, plusEnegry));
            }
            for (int y = steppeHeight ; y < height - steppeHeight ; y++) {
                Vector2d position = new Vector2d(x, y);
                equator.add(new Grass(position, plusEnegry));
            }
            for (int y = height - steppeHeight ; y < height ; y++) {
                Vector2d position = new Vector2d(x, y);
                steppes.add(new Grass(position, plusEnegry));
            }
        }
    }

    public void grassEaten(Vector2d position) {
        if (position.y < steppeHeight || position.y >= height - steppeHeight) {
            steppes.add(new Grass(position, plusEnegry));
        }
        else {
            equator.add(new Grass(position, plusEnegry));
        }
    }


    @Override
    public boolean canAddGrass() {
        return (equator.size() > 0 || steppes.size() > 0);
    }

    @Override
    public Vector2d addGrass() {
        int rand = (int) (Math.random()*10);
        if (steppes.size() == 0) {
            int idx = (int) (Math.random()*equator.size());
            Grass grass = equator.remove(idx);
            return grass.position;
        }
        else if (equator.size() == 0) {
            int idx = (int) (Math.random()*steppes.size());
            Grass grass = steppes.remove(idx);
            return grass.position;
        }
        else if (rand < 8) {
            int idx = (int) (Math.random()*equator.size());
            Grass grass = equator.remove(idx);
            return grass.position;
        }
        else {
            int idx = (int) (Math.random()*steppes.size());
            Grass grass = steppes.remove(idx);
            return grass.position;
        }
    }

    @Override
    public void animalDead(Vector2d position) {};
}
