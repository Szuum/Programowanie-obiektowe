package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Animal {

    private MapDirection orient;
    protected Vector2d position; // czemu to wszystko jest protected, skoro z Animal nic nie dziedziczy?
    protected Map map; // final?
    protected int energy; // czemu to nie jest prywatne?
    protected int genes[]; // przydałaby się osobna klasa na genom
    private int activeGenIdx;
    protected int eatenGrass = 0;
    protected int numbereOfChildren = 0;
    protected int daysAlive = 0;
    protected int deathDay;
//    private List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(Vector2d position, Map map, int energy, int genomSize) {
        this.position = position;
        this.map = map;
        this.energy = energy;
        int startOrient = (int) (Math.random() * 8);
        orient = switch (startOrient) { // MapDirection.values()
            case 0 -> MapDirection.NORTH;
            case 1 -> MapDirection.NORTHEAST;
            case 2 -> MapDirection.EAST;
            case 3 -> MapDirection.SOUTHEAST;
            case 4 -> MapDirection.SOUTH;
            case 5 -> MapDirection.SOUTHWEST;
            case 6 -> MapDirection.WEST;
            default -> MapDirection.NORTHWEST; // case 7
        };
        genes = new int[genomSize];
        for (int i = 0; i < genomSize; i++) {
            int gen = (int) (Math.random() * 8);
            genes[i] = gen;
        }
        activeGenIdx = (int) (Math.random() * (genomSize + 1));
    }

    private void changeOrient() {
        for (int i = 0; i < genes[activeGenIdx]; i++) { // można by dodać metodę turn(n) do MapDirection
            orient = orient.next();
        }
    }

    protected void move() {
        changeOrient();
        Vector2d oldPosition = this.position;
        Vector2d newPosition = map.move(this, position.add(orient.toUnitVector()));
        if (!newPosition.equals(oldPosition)) {
            this.position = newPosition;
            map.positionChanged(oldPosition, this);
        }
    }

    protected void changeDirection() { // lepiej przenieść do MapDirection (np. jako opposite)
        orient = switch (this.orient) {
            case NORTH -> MapDirection.SOUTH;
            case NORTHEAST -> MapDirection.SOUTHWEST;
            case NORTHWEST -> MapDirection.SOUTHEAST;
            case SOUTH -> MapDirection.NORTH;
            case SOUTHEAST -> MapDirection.NORTHWEST;
            default -> MapDirection.NORTHEAST; // case SOUTHWEST
        };
    }

    protected void mutation(Mutation mutationVariant, int numberOfMutation) { // mutate
        ArrayList<Integer> notChangedGenes = new ArrayList<>();
        for (int i = 0; i < genes.length; i++) {
            notChangedGenes.add(i);
        }
        for (int i = 0; i < numberOfMutation; i++) {
            int idx = (int) (Math.random() * (notChangedGenes.size() + 1));
            idx = notChangedGenes.get(idx);
            int newGen = mutationVariant.newGen(genes[idx]);
            genes[idx] = newGen;
            notChangedGenes.remove(idx);
        }
    }


    //    public Animal(IWorldMap map) { // po to mamy gita, żeby móc bez obaw usuwać kod
//        this.map=map;
//    }
//
//    public Animal(IWorldMap map, Vector2d initialPosition) {
//        this.map=map;
//        this.position = initialPosition;
//    }
    public String toString() { // czy to jest używane?
        return switch (this.orient) {
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
            default -> "W"; // case WEST
        };
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

//    public void move(MoveDirection direction) {
//        switch(direction) {
//            case LEFT:
//                this.orient = this.orient.previous();
//                break;
//            case RIGHT:
//                this.orient = this.orient.next();
//                break;
//            case FORWARD:
//                Vector2d possibleForwardPosition = this.position.add(this.orient.toUnitVector());
//                if (map.canMoveTo(possibleForwardPosition)) {
//                    Vector2d oldPosition1 = this.position;
//                    position = possibleForwardPosition;
//                    positionChanged(oldPosition1);
//                }
//                break;
//            default: // case BACKWARD
//                Vector2d possibleBackwardPosition = this.position.subtract(this.orient.toUnitVector());
//                if (map.canMoveTo(possibleBackwardPosition)) {
//                    Vector2d oldPosition2 = this.position;
//                    position = possibleBackwardPosition;
//                    positionChanged(oldPosition2);
//                }
//                break;
//        }
//    }

//    boolean isFacing(MapDirection orient) {
//        return this.orient.equals(orient);
//    }
//
//    protected void addObserver(IPositionChangeObserver observer) {
//        observers.add(observer);
//    }
//
//    protected void removeObserver(IPositionChangeObserver observer) {
//        observers.remove(observer);
//    }
//
//    private void positionChanged(Vector2d oldPosition) {
//        for (IPositionChangeObserver observer : observers) {
//            observer.positionChanged(oldPosition, this.position);
//        }
//    }
//

    public Vector2d getPosition() {
        return this.position;
    }
//
//    @Override
//    public String getImagePath() {
//        return switch (this.orient) {
//            case NORTH -> "C:\\Users\\user\\Documents\\GitHub\\Programowanie-obiektowe\\oolab\\oolab\\src\\main\\resources\\up.png";
//            case SOUTH -> "C:\\Users\\user\\Documents\\GitHub\\Programowanie-obiektowe\\oolab\\oolab\\src\\main\\resources\\down.png";
//            case WEST -> "C:\\Users\\user\\Documents\\GitHub\\Programowanie-obiektowe\\oolab\\oolab\\src\\main\\resources\\left.png";
//            case EAST -> "C:\\Users\\user\\Documents\\GitHub\\Programowanie-obiektowe\\oolab\\oolab\\src\\main\\resources\\right.png";
//        };
//    }
}
