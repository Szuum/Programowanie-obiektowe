package agh.ics.oop;

public class Animal {
    private MapDirection orient = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);

    public String toString() {
        String inf = "Pozycja: ";
        inf = inf + this.position.toString();
        inf = inf + ", orientacja: ";
        inf = inf + this.orient.toString();
        return inf;
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
                boolean b = false;
                switch(this.orient) {
                    case NORTH:
                        if (this.position.y < 4) {
                            b = true;
                        }
                        break;
                    case EAST:
                        if (this.position.x < 4) {
                            b = true;
                        }
                        break;
                    case SOUTH:
                        if (this.position.y > 0) {
                            b = true;
                        }
                        break;
                    default:
                        if (this.position.x > 0) {
                            b = true;
                        }
                        break;
                }
                if (b) {
                    this.position = this.position.add(this.orient.toUnitVector());
                }
                break;
            default:
                boolean tmp = false;
                switch(this.orient) {
                    case NORTH:
                        if (this.position.y > 0) {
                            tmp = true;
                        }
                        break;
                    case EAST:
                        if (this.position.x > 0) {
                            tmp = true;
                        }
                        break;
                    case SOUTH:
                        if (this.position.y < 4) {
                            tmp = true;
                        }
                        break;
                    default:
                        if (this.position.x < 4) {
                            tmp = true;
                        }
                        break;
                }
            if (tmp) {
                this.position = this.position.add(this.orient.toUnitVector().opposite());
            }
            break;
        }
    }

    public boolean isFacing(MapDirection orient) {
        return this.orient.equals(orient);
    }
}
