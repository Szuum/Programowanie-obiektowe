package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimulationTest {

    @Test
    public void wrongSpawnTest() {
        String[] moves = {"f", "f", "right", "b"};
        MoveDirection[] directions = new OptionsParser().parse(moves);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2,2) };
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Assertions.assertEquals(engine.animals.size(), 1);
        Assertions.assertEquals(engine.animals.get(0).getPosition(), new Vector2d(1, 4));
        Assertions.assertTrue(engine.animals.get(0).isFacing(MapDirection.EAST));
    }

    @Test
    public void samePositionTest() {
        String[] moves = {"r", "f"};
        MoveDirection[] directions = new OptionsParser().parse(moves);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,3), new Vector2d(2,2) };
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Assertions.assertEquals(engine.animals.get(0).getPosition(), new Vector2d(2, 3));
        Assertions.assertEquals(engine.animals.get(1).getPosition(), new Vector2d(2, 2));
    }

    @Test
    public void bordersTest() {
        String[] moves = {"l", "r", "r", "f", "f", "r", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(moves);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(0,3), new Vector2d(3,0), new Vector2d(10, 3), new Vector2d(5, 5)};
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Assertions.assertEquals(engine.animals.get(0).getPosition(), new Vector2d(0, 3));
        Assertions.assertEquals(engine.animals.get(1).getPosition(), new Vector2d(3, 0));
        Assertions.assertEquals(engine.animals.get(2).getPosition(), new Vector2d(10, 3));
        Assertions.assertEquals(engine.animals.get(3).getPosition(), new Vector2d(5, 5));
    }

    @Test
    public void movesTest() {
        String[] moves = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(moves);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Assertions.assertEquals(engine.animals.get(0).getPosition(), new Vector2d(2, 0));
        Assertions.assertEquals(engine.animals.get(1).getPosition(), new Vector2d(3, 5));
        Assertions.assertTrue(engine.animals.get(0).isFacing(MapDirection.SOUTH));
        Assertions.assertTrue(engine.animals.get(1).isFacing(MapDirection.NORTH));
    }
}
