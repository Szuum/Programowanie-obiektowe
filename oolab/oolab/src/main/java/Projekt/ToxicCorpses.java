package Projekt;

import java.util.ArrayList;
import java.util.HashMap;

public class ToxicCorpses implements IGrowVariant {
    private final int width;
    private final int height;
    private final int plusEnegry;
    private ArrayList<GrassField> freePosition = new ArrayList<>();
    private HashMap<Vector2d, GrassField> occupiedPosition = new HashMap<>();

    public ToxicCorpses(int width, int height, int plusEnegry) {
        this.width = width;
        this.height = height;
        this.plusEnegry = plusEnegry;
        for (int x = 0 ; x < width ; x++) {
            for (int y = 0 ; y < height ; y++) {
                GrassField grassField = new GrassField(new Vector2d(x, y));
                freePosition.add(grassField);
            }
        }
    }

    private void sortArray() {
        freePosition.sort((f1, f2) -> {
            return f1.deadAnimal - f2.deadAnimal;
        });
    }


    @Override
    public Vector2d addGrass() {
        int rand = (int) (Math.random()*10);
        int size1 = (int) (freePosition.size()*0.8);
        int size2 = freePosition.size() - size1;
        int idx;
        if (rand < 8) {
            idx = (int) (Math.random()*size1);
        }
        else {
            idx = freePosition.size() - 1 - (int) (Math.random()*size2);
        }
        GrassField grassField = freePosition.remove(idx);
        occupiedPosition.put(grassField.position, grassField);
        return grassField.position;
    }

    @Override
    public boolean canAddGrass() {
        return !freePosition.isEmpty();
    }

    @Override
    public void grassEaten(Vector2d position) {
        GrassField grassField = occupiedPosition.remove(position);
        freePosition.add(grassField);
    }

    @Override
    public void animalDead(Vector2d position) {
        if (occupiedPosition.containsKey(position)) {
            occupiedPosition.get(position).deadAnimal++;
        }
        else {
            for (GrassField grassField : freePosition) {
                if (grassField.position.equals(position)) {
                    grassField.deadAnimal++;
                    break;
                }
            }
        }
    }
}
