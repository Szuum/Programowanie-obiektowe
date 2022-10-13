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
        return this.x <= other.x && this.y <= other.y;
    }

    boolean follows(Vector2d other) {
        return this.x >= other.x && this.y >= other.y;
    }

    Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    Vector2d upperRight(Vector2d other) {
        return new Vector2d(Math.max(this.x, other.x), Math.max(this.y, this.y));
    }

    Vector2d lowerLeft(Vector2d other) {
        return new Vector2d(Math.min(this.x, other.x), Math.min(this.y, other.y));
    }

    Vector2d opposite() {
        return new Vector2d(-this.x, -this.y);
    }

//    boolean equals(Object other) {
//        return this.x==other.x && this.y==other.y;
//    }
}
