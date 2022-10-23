package agh.ics.oop;

import java.util.Arrays;

public class OptionsParser {
    public MoveDirection[] parse(String data[]) {
        MoveDirection[] direction = new MoveDirection[data.length];
        int wrong_data = 0;
        for (int i = 0 ; i < data.length ; i++) {
            String argument = data[i];
            switch (argument) {
                case "f", "forward":
                    direction[i - wrong_data] = MoveDirection.FORWARD;
                    break;
                case "b", "backward":
                    direction[i - wrong_data] = MoveDirection.BACKWARD;
                    break;
                case "r", "right":
                    direction[i - wrong_data] = MoveDirection.RIGHT;
                    break;
                case "l", "left":
                    direction[i - wrong_data] = MoveDirection.LEFT;
                    break;
                default:
                    wrong_data++;
                    break;
            }
        }
        return Arrays.copyOfRange(direction, 0, data.length - wrong_data);
    }
}
