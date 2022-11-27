package agh.ics.oop;

import java.util.Arrays;

public class OptionsParser {
    public MoveDirection[] parse(String[] data) {
        MoveDirection[] direction = new MoveDirection[data.length];
        int wrong_data = 0;
        for (int i = 0 ; i < data.length ; i++) {
            String argument = data[i];
            switch (argument) {
                case "f", "forward" -> direction[i - wrong_data] = MoveDirection.FORWARD;
                case "b", "backward" -> direction[i - wrong_data] = MoveDirection.BACKWARD;
                case "r", "right" -> direction[i - wrong_data] = MoveDirection.RIGHT;
                case "l", "left" -> direction[i - wrong_data] = MoveDirection.LEFT;
                default -> throw new IllegalArgumentException(argument + " is not legal move specification");
            }
        }
        return Arrays.copyOfRange(direction, 0, data.length - wrong_data);
    }
}
