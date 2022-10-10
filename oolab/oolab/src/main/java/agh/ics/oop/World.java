package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        System.out.println("System wystartował");
        Direction[] t = new Direction[args.length];
        for (int i=0 ; i<args.length ; i++) {
            t[i] = switch (args[i]) {
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "r" -> Direction.RIGHT;
                case "l" -> Direction.LEFT;
            };
        }
        run(t);
        System.out.println("System zakończył działanie");
    }
    static void run(Direction tab[]) {
        for (int i=0; i<tab.length; i++) {
            Direction argument = tab[i];
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
}
