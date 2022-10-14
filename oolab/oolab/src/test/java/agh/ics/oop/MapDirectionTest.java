package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapDirectionTest {

    @Test
    void next_test() {
        Assertions.assertEquals(MapDirection.NORTH.next(), MapDirection.EAST);
        Assertions.assertEquals(MapDirection.EAST.next(), MapDirection.SOUTH);
        Assertions.assertEquals(MapDirection.SOUTH.next(), MapDirection.WEST);
        Assertions.assertEquals(MapDirection.WEST.next(), MapDirection.NORTH);
    }

    @Test
    void previosu_test() {
        Assertions.assertEquals(MapDirection.NORTH.previous(), MapDirection.WEST);
        Assertions.assertEquals(MapDirection.EAST.previous(), MapDirection.NORTH);
        Assertions.assertEquals(MapDirection.SOUTH.previous(), MapDirection.EAST);
        Assertions.assertEquals(MapDirection.WEST.previous(), MapDirection.SOUTH);
    }
}
