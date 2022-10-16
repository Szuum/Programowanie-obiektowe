package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Vector2dTest {

    @Test
    public void equalsTest() {
        Vector2d vector1 = new Vector2d(4, 5);
        Vector2d vector2 = new Vector2d(4, 5);
        Vector2d vector3 = new Vector2d(4, 6);
        Vector2d vector4 = new Vector2d(-4, 5);
        MapDirection direction = MapDirection.EAST;

        Assertions.assertTrue(vector1.equals(vector2));
        Assertions.assertFalse(vector1.equals(vector3));
        Assertions.assertFalse(vector1.equals(vector4));
        Assertions.assertTrue(vector1.equals(vector1));
        Assertions.assertFalse(vector1.equals(direction));
    }

    @Test
    public void toStringTest() {
        Vector2d vector1 = new Vector2d(3, 5);
        Vector2d vector2 = new Vector2d(-3, -5);
        Vector2d vector3 = new Vector2d(0, -17);

        Assertions.assertEquals(vector1.toString(), "(3,5)");
        Assertions.assertEquals(vector2.toString(), "(-3,-5)");
        Assertions.assertEquals(vector3.toString(), "(0,-17)");
    }

    @Test
    public void precedesTest() {
        Vector2d vector1 = new Vector2d(4, 6);
        Vector2d vector2 = new Vector2d(-5, 0);
        Vector2d vector3 = new Vector2d(4, 5);
        Vector2d vector4 = new Vector2d(3, 6);
        Vector2d vector5 = new Vector2d(4, 6);

        Assertions.assertFalse(vector1.precedes(vector2));
        Assertions.assertFalse(vector1.precedes(vector3));
        Assertions.assertFalse(vector1.precedes(vector4));
        Assertions.assertTrue(vector1.precedes(vector5));
        Assertions.assertTrue(vector2.precedes(vector4));
        Assertions.assertTrue(vector1.precedes(vector1));
        Assertions.assertFalse(vector4.precedes(vector3));
        Assertions.assertFalse(vector3.precedes(vector4));
    }

    @Test
    public void followsTest() {
        Vector2d vector1 = new Vector2d(4, 6);
        Vector2d vector2 = new Vector2d(-5, 0);
        Vector2d vector3 = new Vector2d(4, 5);
        Vector2d vector4 = new Vector2d(3, 6);
        Vector2d vector5 = new Vector2d(4, 6);

        Assertions.assertTrue(vector1.follows(vector2));
        Assertions.assertTrue(vector1.follows(vector3));
        Assertions.assertTrue(vector1.follows(vector4));
        Assertions.assertTrue(vector1.follows(vector5));
        Assertions.assertFalse(vector2.follows(vector4));
        Assertions.assertTrue(vector1.follows(vector1));
        Assertions.assertFalse(vector4.follows(vector3));
        Assertions.assertFalse(vector3.follows(vector4));
    }

    @Test
    public void upperRightTest() {
        Vector2d vector1 = new Vector2d(4, 6);
        Vector2d vector2 = new Vector2d(7, 0);
        Vector2d vector3 = new Vector2d(8, 10);
        Vector2d vector4 = new Vector2d(4, 3);
        Vector2d vector5 = new Vector2d(6, 6);

        Assertions.assertEquals(vector1.upperRight(vector2), new Vector2d(7, 6));
        Assertions.assertEquals(vector1.upperRight(vector3), new Vector2d(8,10));
        Assertions.assertEquals(vector1.upperRight(vector4), new Vector2d(4,6));
        Assertions.assertEquals(vector1.upperRight(vector5), new Vector2d(6,6));
        Assertions.assertEquals(vector1.upperRight(vector1), new Vector2d(4,6));
        Assertions.assertEquals(vector3.upperRight(vector1), new Vector2d(8, 10));
    }

    @Test
    public void lowerLeftTest() {
        Vector2d vector1 = new Vector2d(4, 6);
        Vector2d vector2 = new Vector2d(7, 0);
        Vector2d vector3 = new Vector2d(8, 10);
        Vector2d vector4 = new Vector2d(4, 3);
        Vector2d vector5 = new Vector2d(6, 6);

        Assertions.assertEquals(vector1.lowerLeft(vector2), new Vector2d(4, 0));
        Assertions.assertEquals(vector1.lowerLeft(vector3), new Vector2d(4,6));
        Assertions.assertEquals(vector1.lowerLeft(vector4), new Vector2d(4,3));
        Assertions.assertEquals(vector1.lowerLeft(vector5), new Vector2d(4,6));
        Assertions.assertEquals(vector1.lowerLeft(vector1), new Vector2d(4,6));
        Assertions.assertEquals(vector3.lowerLeft(vector1), new Vector2d(4,6));
    }

    @Test
    public void addTest() {
        Vector2d vector1 = new Vector2d(6, 4);
        Vector2d vector2 = new Vector2d(-3, -1);
        Vector2d vector3 = new Vector2d(0, 5);

        // wektory testowe
        Vector2d vector4 = vector1.add(vector2);
        Vector2d vector5 = vector1.add(vector3);
        Vector2d vector6 = vector2.add(vector3);
        Vector2d vector7 = vector2.add(vector1);
        Vector2d vector8 = vector1.add(vector1);

        Assertions.assertEquals(vector4, new Vector2d(3, 3));
        Assertions.assertEquals(vector5, new Vector2d(6, 9));
        Assertions.assertEquals(vector6, new Vector2d(-3, 4));
        Assertions.assertEquals(vector7, new Vector2d(3,3));
        Assertions.assertEquals(vector8, new Vector2d(12, 8));
    }

    @Test
    public void substractTest() {
        Vector2d vector1 = new Vector2d(6, 4);
        Vector2d vector2 = new Vector2d(-3, -1);
        Vector2d vector3 = new Vector2d(0, 5);

        // wektory testowe
        Vector2d vector4 = vector1.subtract(vector2);
        Vector2d vector5 = vector1.subtract(vector3);
        Vector2d vector6 = vector2.subtract(vector3);
        Vector2d vector7 = vector2.subtract(vector1);
        Vector2d vector8 = vector3.subtract(vector1);
        Vector2d vector9 = vector3.subtract(vector2);
        Vector2d vector10 = vector1.subtract(vector1);

        Assertions.assertEquals(vector4, new Vector2d(9, 5));
        Assertions.assertEquals(vector5, new Vector2d(6, -1));
        Assertions.assertEquals(vector6, new Vector2d(-3, -6));
        Assertions.assertEquals(vector7, new Vector2d(-9, -5));
        Assertions.assertEquals(vector8, new Vector2d(-6, 1));
        Assertions.assertEquals(vector9, new Vector2d(3, 6));
        Assertions.assertEquals(vector10, new Vector2d(0, 0));
    }

    @Test
    public void oppositeTest() {
        Vector2d vector1 = new Vector2d(3, 4);
        Vector2d vector2 = new Vector2d(-2, -1);
        Vector2d vector3 = new Vector2d(-1, 0);
        Vector2d vector4 = new Vector2d(0, 5);
        Vector2d vector5 = new Vector2d(0, 0);

        Assertions.assertEquals(vector1.opposite(), new Vector2d(-3, -4));
        Assertions.assertEquals(vector2.opposite(), new Vector2d(2, 1));
        Assertions.assertEquals(vector3.opposite(), new Vector2d(1, 0));
        Assertions.assertEquals(vector4.opposite(), new Vector2d(0, -5));
        Assertions.assertEquals(vector5.opposite(), new Vector2d(0, 0));
    }
}