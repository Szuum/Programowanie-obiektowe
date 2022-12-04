package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement {

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
                Vector2d possibleForwardPosition = this.position.add(this.orient.toUnitVector());
                if (map.canMoveTo(possibleForwardPosition)) {
                    Vector2d oldPosition1 = this.position;
                    position = possibleForwardPosition;
                    positionChanged(oldPosition1);
                }
                break;
            default: // case BACKWARD
                Vector2d possibleBackwardPosition = this.position.subtract(this.orient.toUnitVector());
                if (map.canMoveTo(possibleBackwardPosition)) {
                    Vector2d oldPosition2 = this.position;
                    position = possibleBackwardPosition;
                    positionChanged(oldPosition2);
                }
                break;
        }
    }

    boolean isFacing(MapDirection orient) {
        return this.orient.equals(orient);
    }

    protected void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    protected void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition) {
        for (IPositionChangeObserver observer : observers) {
            observer.positionChanged(oldPosition, this.position);
        }
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public String getImagePath() {
        return switch (this.orient) {
            case NORTH -> "src/main/resources/up.png";
            case SOUTH -> "src/main/resources/down.png";
            case WEST -> "src/main/resources/left.png";
            case EAST -> "src/main/resources/right.png";
        };
    }
}
