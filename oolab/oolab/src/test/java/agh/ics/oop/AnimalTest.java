package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnimalTest {

    @Test
    public void animalTest() {
        String[] test = {"r", "right", "forward", "l", "x", "f", "f", "f", "left", "forward", "f", "r", "backward", "l",
            "f", "forward", "r", "z", "b", "backward", "r", "f", "forward", "r", "f", "f", "right", "b", "b", "backward", "left"};
        OptionsParser parse = new OptionsParser();
        MoveDirection[] moves = parse.parse(test);
        Animal animal = new Animal();

        for (int i = 0 ; i < moves.length ; i++) {
            animal.move(moves[i]);
        }
        Assertions.assertTrue((animal.isAt(new Vector2d(0, 0))) && animal.isFacing(MapDirection.WEST));
    }
}
