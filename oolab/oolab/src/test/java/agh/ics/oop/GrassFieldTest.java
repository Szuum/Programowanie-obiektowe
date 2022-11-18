package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GrassFieldTest {

    @Test
    public void placeTest() {
        IWorldMap map = new GrassField(10);
        map.place(new Animal(map, new Vector2d(2, 2)));
        Assertions.assertTrue(map.place(new Animal(map, new Vector2d(2, 4))));
        Assertions.assertFalse(map.place(new Animal(map, new Vector2d(2, 2))));
    }

    @Test
    public void canMoveToTest() {
        IWorldMap map = new GrassField(10);
        map.place(new Animal(map, new Vector2d(2, 2)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(3, 4)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(2, 2)));
    }

    @Test
    public void isOccupiedTest() {
        GrassField map = new GrassField(0);
        map.place(new Animal(map, new Vector2d(2, 2)));
        map.grasses.put((new Vector2d(3, 4)), new Grass(new Vector2d(3, 4)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(2, 2)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(3, 4)));
        Assertions.assertFalse(map.isOccupied(new Vector2d(5, 5)));
    }

    @Test
    public void objectAtTest() {
        GrassField map = new GrassField(0);
        Animal animal = new Animal(map, new Vector2d(2, 2));
        map.place(animal);
        Grass grass = new Grass(new Vector2d(3, 4));
        map.grasses.put(new Vector2d(3, 4), grass);
        Assertions.assertNull(map.objectAt(new Vector2d(2, 3)));
        Assertions.assertEquals(map.objectAt(new Vector2d(2, 2 )), animal);
        Assertions.assertEquals(map.objectAt(new Vector2d(3, 4)), grass);
    }
}
