package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartował");
        run();
        System.out.println("system zakończył działanie");

    }
    static void run(String A[]) {
        System.out.println("zwierzak idzie do przodu");
        for (String elem : A) {
            System.out.print(elem);
            System.out.print(", ");
        }
    }
}
