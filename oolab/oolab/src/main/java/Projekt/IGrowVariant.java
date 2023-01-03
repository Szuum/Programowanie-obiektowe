package Projekt;

public interface IGrowVariant {
    Vector2d addGrass(); // dodanie trawy
    boolean canAddGrass(); // sprawdzenie, czy można gdzieś dodać trawę
    void grassEaten(Vector2d position); // trawa została zjedzona

    void animalDead(Vector2d position); // zwierzę umarło na danym polu
}
