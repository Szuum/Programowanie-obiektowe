package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangularMapTest {

    @Test
    public void placeTest() {
        IWorldMap map = new RectangularMap(10, 5);
        map.place(new Animal(map, new Vector2d(2, 2)));
        Assertions.assertFalse(map.place(new Animal(map, new Vector2d(100, 100))));
        Assertions.assertTrue(map.place(new Animal(map, new Vector2d(2, 4))));
        Assertions.assertFalse(map.place(new Animal(map, new Vector2d(2, 2))));
    }

    @Test
    public void canMoveToTest() {
        IWorldMap map = new RectangularMap(10, 5);
        map.place(new Animal(map, new Vector2d(2, 2)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(3, 4)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(2, 2)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(11, 4)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(3, 6)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(-2, 4)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(4, -1)));
    }

    @Test
    public void isOccupiedTest() {
        IWorldMap map = new RectangularMap(10, 5);
        map.place(new Animal(map, new Vector2d(2, 2)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(2, 2)));
        Assertions.assertFalse(map.isOccupied(new Vector2d(3, 4)));
    }

    @Test
    public void objectAtTest() {
        IWorldMap map = new RectangularMap(10, 5);
        Animal animal = new Animal(map, new Vector2d(2, 2));
        map.place(animal);
        Assertions.assertNull(map.objectAt(new Vector2d(2, 3)));
        Assertions.assertEquals(map.objectAt(new Vector2d(2, 2 )), animal);
    }
}
