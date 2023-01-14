package Projekt;

public class Globe extends AbstractMap {

    private final int width;
    private final int height;

    public Globe(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    protected Vector2d newPosition(Animal animal, Vector2d newPosition) { // znajdowanie nowej pozycji zwierzaka
        if (newPosition.follows(new Vector2d(0, 0)) && newPosition.precedes(new Vector2d(width - 1, height - 1))) {
            return newPosition; // pozycja znajduje się na mapie
        }
        int x = (newPosition.x + width) % width;
        int y;
        if (newPosition.y >= 0 && newPosition.y < height) { // zwierzę przechodzi na drugą stronę świata
            y = newPosition.y;
        } else { // zwierzę odbija się bieguna
            y = animal.position.y;
            animal.oppositeOrient();
        }
        return new Vector2d(x, y);
    }
}