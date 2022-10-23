package agh.ics.oop;

import java.util.Arrays;

public class World {

    public static Direction[] convert(String[] dane, Direction[] directions) {
        int wrong_case = 0;
        for (int i=0 ; i<dane.length ; i++) {
            String argument = dane[i];
            switch (argument) {
                case "f":
                    directions[i - wrong_case] = Direction.FORWARD;
                    break;
                case "b":
                    directions[i - wrong_case] = Direction.BACKWARD;
                    break;
                case "r":
                    directions[i - wrong_case] = Direction.RIGHT;
                    break;
                case "l":
                    directions[i - wrong_case] = Direction.LEFT;
                    break;
                default:
                    wrong_case++;
            }
        }
        return Arrays.copyOfRange(directions, 0, directions.length - wrong_case);
    }

    static void run(Direction directions[]) {
        for (int i=0; i<directions.length; i++) {
            Direction argument = directions[i];
            switch (argument) {
                case FORWARD:
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case BACKWARD:
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
                case RIGHT:
                    System.out.println("Zwierzak skręca w prawo");
                    break;
                case LEFT:
                    System.out.println("Zwierzak skręca w lewo");
                    break;
            }
        }
    }
    public static void main(String[] args) {

//        Vector2d position1 = new Vector2d(1,2);
//        System.out.println(position1);
//        Vector2d position2 = new Vector2d(-2,1);
//        System.out.println(position2);
//        System.out.println(position1.add(position2));

        System.out.println("System wystartował");

        Animal animal = new Animal();
//        animal.move(MoveDirection.RIGHT);
//        animal.move(MoveDirection.FORWARD);
//        animal.move(MoveDirection.FORWARD);
//        animal.move(MoveDirection.FORWARD);
//        System.out.println(animal.toString());
//
//        Direction[] kierunki = new Direction[args.length];
//        Direction[] directions = convert(args, kierunki);
//        run(directions);


        OptionsParser toParse = new OptionsParser();
        MoveDirection[] direction = toParse.parse(args);
        for (int i = 0 ; i < direction.length ; i++) {
            animal.move(direction[i]);
            System.out.println(animal.toString());
        }

        System.out.println("System zakończył działanie");
    }

/*
    Jak sprawić, żeby zwierzęta nie znalazły się na tym samym polu:
    boolean places[][] = new boolean[5][5];

    for (int i = 0 ; i < 5 ; i++) {
        for (int j = 0 ; j < 5 ; j++) {
            places[i][j] = true;
        }
    }
    Animal animal = new Animal();
    places[2][2] = false;
    MoveDirection[] direction = toParse.parse(args);
    for (int i = 0 ; i < direction.length ; i++) {
        Vector2d startPositon = animal.getPosition();
        int startX = startPositon.x;
        int startY = startPositon.y;
        animal.move(direction[i]);
        Vector2d position = animal.getPosition();
        int x = position.x;
        int y = position.y;
        if (direction[i].equals(MoveDirection.FORWARD)) {
            if (places[x][y] == false) {
                animal.move(MoveDirection.BACKWARD);
            }
            else {
                places[startX][startY] = true;
                places[x][y] = false;
            }
        }
        else if (direction[i].equals(MoveDirection.BACKWARD)) {
            if (places[x][y] == false) {
                animal.move(MoveDirection.FORWARD);
            } else {
                places[startX][startY] = true;
                places[x][y] = false;
            }
        }
    }
  */
}
