package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        System.out.println("System wystartował");
        Direction t[] = {Direction.BACKWARD, Direction.FORWARD, Direction.BACKWARD, Direction.LEFT, Direction.RIGHT};
        run(t);
        System.out.println("System zakończył działanie");
    }
    static void run(Direction tab[]) {
        int n=tab.length;
        for (int i=0; i<n; i++) {
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
