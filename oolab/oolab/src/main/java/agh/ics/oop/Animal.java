package agh.ics.oop;

public class Animal {

    private MapDirection orient = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);
    public IWorldMap map;

    public Animal(IWorldMap map) {
        this.map=map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map=map;
        this.position = initialPosition;
    }
    public String toString() {
        switch (this.orient) {
            case NORTH:
                return "N";
            case EAST:
                return "E";
            case SOUTH:
                return "S";
            default: // case WEST
                return "W";
        }
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
                    position = this.position.add(this.orient.toUnitVector());
                }
                break;
            default: // case BACKWARD
                if (map.canMoveTo(this.position.subtract(this.orient.toUnitVector()))) {
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
}
