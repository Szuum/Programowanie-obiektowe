package agh.ics.oop;

public class Vector2d {
    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public final int x;
    public final int y;

    public String toString() {
        return ("(" + (String.valueOf(this.x)) + ", " + String.valueOf(this.y) + ")");
    }

    boolean precedes(Vector2d other) {
        if (this.x <= other.x && this.y <= other.y) {
            return true;
        }
        return false;
    }

    boolean follows(Vector2d other) {
        if (this.x >= other.x && this.y >= other.y) {
            return true;
        }
        return false;
    }

    Vector2d upperRight(Vector2d other) {
        return new Vector2d(Math.max(this.x, other.x), Math.max(this.y, this.y));
    }


}
