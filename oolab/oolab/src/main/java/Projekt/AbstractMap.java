package Projekt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public abstract class AbstractMap {
    ArrayList<Animal> animals = new ArrayList<>();
    HashMap<Vector2d, Grass> grasses = new HashMap<>();

    public void place(Animal animal) {
        animals.add(animal);
    }

    protected boolean deleteAnimal(Animal animal) {
        if (animal.energy <= 0) {
            animals.remove(animal);
            return true;
        }
        return false;
    }
//
//    protected Object objectAt(Vector2d position) {
//        for (Animal animal : animals) {
//            if (animal.position.equals(position)) {
//                return animal;
//            }
//        }
//        return null;
//    }

    protected abstract Vector2d newPosition(Animal animal, Vector2d newPosition);

}
