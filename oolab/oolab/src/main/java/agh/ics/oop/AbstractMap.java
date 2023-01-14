package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

public abstract class AbstractMap {
    public Vector2d move(Map map, Animal animal, Vector2d newPosition) {
        Vector2d oldPosition = animal.position;
        if (newPosition.precedes(new Vector2d(map.width, map.height))) { // nowy obiekt co wywołanie
            return newPosition;
        }
        return oldPosition; // ??
    }

    //
//    public boolean canMoveTo(Vector2d position) {
//        return !(objectAt(position) instanceof Animal);
//    }
    public abstract void consumption(Map map); // dlaczego metoda mapy przyjmuje mapę?

//    public boolean isOccupied(Vector2d position) {
//        return objectAt(position)!= null;
//    }

//    public Object objectAt(Vector2d position) {
//        return animals.get(position);
//    }

}
