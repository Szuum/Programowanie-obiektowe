package Projekt;

public class HellPortal extends AbstractMap{

    private final int width;
    private final int height;

    public HellPortal(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    protected Vector2d newPosition(Animal animal, Vector2d newPosition) { // szukanie nowej pozycji zwierzęcia
        if (newPosition.follows(new Vector2d(0, 0)) && newPosition.precedes(new Vector2d(width - 1, height - 1))) {
            return newPosition; // zwierzę mieści się na mapie
        }
        int x = (int) (Math.random()*width); // nowa współrzędna x
        int y = (int) (Math.random()*height); // nowa współrzędna y
        animal.energy -= animal.lostEnergy; // starta energii
        return new Vector2d(x, y);
    }
}
