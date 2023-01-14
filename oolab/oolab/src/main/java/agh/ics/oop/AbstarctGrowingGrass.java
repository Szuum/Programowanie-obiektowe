package agh.ics.oop; // duży ten pakiet

public abstract class AbstarctGrowingGrass {
    protected int width;
    protected int height;

    public AbstarctGrowingGrass(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public abstract Vector2d grownGrass();

    protected abstract void deleteGrass(Vector2d position);

}
