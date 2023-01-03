package Projekt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class ToxicCorpses implements IGrowVariant {
    private final int width;
    private final int height;
    private final int plusEnegry;
    private ArrayList<GrassField> freePosition = new ArrayList<>(); // wolne pozycje
    private HashMap<Vector2d, GrassField> occupiedPosition = new HashMap<>(); // zajmowane pozycje

    public ToxicCorpses(int width, int height, int plusEnegry) {
        this.width = width;
        this.height = height;
        this.plusEnegry = plusEnegry;
        for (int x = 0 ; x < width ; x++) { // dodawanie pól do wolnych pozycji
            for (int y = 0 ; y < height ; y++) {
                GrassField grassField = new GrassField(new Vector2d(x, y));
                freePosition.add(grassField);
            }
        }
    }

    private void sortArray() { // sortowanie po ilości zdechłych zwierząt na polu
        freePosition.sort(Comparator.comparingInt(f -> f.deadAnimal));
    }


    @Override
    public Vector2d addGrass() { // dodanie trawy
        sortArray();
        int rand = (int) (Math.random()*10);
        int size1 = (int) (freePosition.size()*0.8);
        int size2 = freePosition.size() - size1;
        int idx;
        if (rand < 8) { // pole preferowane
            idx = (int) (Math.random()*size1);
        }
        else { // pole niepreferowane
            idx = freePosition.size() - 1 - (int) (Math.random()*size2);
        }
        GrassField grassField = freePosition.remove(idx);
        occupiedPosition.put(grassField.position, grassField);
        return grassField.position;
    }

    @Override
    public boolean canAddGrass() { // sprawdzenie, czy można dodać trawę
        return !freePosition.isEmpty();
    }

    @Override
    public void grassEaten(Vector2d position) { // zjedzenie trawy na danym polu
        GrassField grassField = occupiedPosition.remove(position);
        freePosition.add(grassField);
    }

    @Override
    public void animalDead(Vector2d position) { // zwierzę umarło na danej pozycji
        if (occupiedPosition.containsKey(position)) { // na tej pozycji rośnie trawa
            occupiedPosition.get(position).deadAnimal++;
        }
        else {
            for (GrassField grassField : freePosition) { // na tej pozycji nie ma trawy
                if (grassField.position.equals(position)) {
                    grassField.deadAnimal++;
                    break;
                }
            }
        }
    }
}