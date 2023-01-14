package agh.ics.oop;

public interface IGrowingGrass { // czy ten interfejs jest potrzebny, skoro jest klasa abstrakcyjna?

    Vector2d grownGrass();

    void deleteGrass(Vector2d position);
}
