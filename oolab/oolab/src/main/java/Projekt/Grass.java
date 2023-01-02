package Projekt;

public class Grass implements IMapElement{
    protected final Vector2d position;
    protected final int plusEnergy;
    public Grass(Vector2d position, int plusEnergy) {
        this.position = position;
        this.plusEnergy = plusEnergy;
    }

    @Override
    public String getImagePath() {
        return "src/main/resources/grass.png";
    }

    @Override
    public String getData() {
        return "Grass";
    }
}
