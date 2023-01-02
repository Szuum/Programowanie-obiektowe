package Projekt;

public interface IGrowVariant {
    Vector2d addGrass();
    boolean canAddGrass();
    void grassEaten(Vector2d position);

    void animalDead(Vector2d position);
}
