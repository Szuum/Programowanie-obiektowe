package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal {

    private MapDirection orient = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);
    public IWorldMap map;
    private List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(IWorldMap map) {
        this.map=map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map=map;
        this.position = initialPosition;
    }
    public String toString() {
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

    public void move(MoveDirection direction) {
        switch(direction) {
            case LEFT:
                this.orient = this.orient.previous();
                break;
            case RIGHT:
                this.orient = this.orient.next();
                break;
            case FORWARD:
                if (map.canMoveTo(this.position.add(this.orient.toUnitVector()))) {
                    positionChanged(this.position.add(this.orient.toUnitVector()));
                    position = this.position.add(this.orient.toUnitVector());
                }
                break;
            default: // case BACKWARD
                if (map.canMoveTo(this.position.subtract(this.orient.toUnitVector()))) {
                    positionChanged(this.position.subtract(this.orient.toUnitVector()));
                    position = this.position.subtract(this.orient.toUnitVector());
                }
                break;
        }
    }

    boolean isFacing(MapDirection orient) {
        return this.orient.equals(orient);
    }

    Vector2d getPosition() {
        return this.position;
    }

    protected void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    protected void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    protected void positionChanged(Vector2d newPosition) {
        for (IPositionChangeObserver observer : observers) {
            observer.positionChanged(this.position, newPosition);
        }
    }
}
