package Projekt;

public class HellPortal extends AbstractMap{

    private final int width;
    private final int height;

    public HellPortal(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    protected Vector2d newPosition(Animal animal, Vector2d newPosition) {
        if (newPosition.follows(new Vector2d(0, 0)) && newPosition.precedes(new Vector2d(width - 1, height - 1))) {
            return newPosition;
        }
        int x = (int) (Math.random()*width);
        int y = (int) (Math.random()*height);
        animal.energy -= animal.lostEnergy;
        return new Vector2d(x, y);
    }
}
