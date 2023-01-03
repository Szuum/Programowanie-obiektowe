package Projekt;

import java.util.ArrayList;

public abstract class AbstractMap {
    ArrayList<Animal> animals = new ArrayList<>();

    public void place(Animal animal) {
        animals.add(animal);
    } // dodaje zwierzaka do mapy

    protected boolean deleteAnimal(Animal animal) { // usuwa zwierzaka z mapy
        if (animal.energy <= 0) {
            animals.remove(animal);
            return true;
        }
        return false;
    }

    protected abstract Vector2d newPosition(Animal animal, Vector2d newPosition); // ustala nową pozycję w zalożoności od rodzaju mapy

}
