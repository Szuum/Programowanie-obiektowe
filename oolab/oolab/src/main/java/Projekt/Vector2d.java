package Projekt;

import java.util.Objects;

public class Vector2d {

    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return ("(" + (String.valueOf(this.x)) + "," + String.valueOf(this.y) + ")");
    }

    public boolean precedes(Vector2d other) {
        return this.x <= other.x && this.y <= other.y;
    }

    public boolean follows(Vector2d other) {
        return this.x >= other.x && this.y >= other.y;
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    @Override
    public boolean equals(Object other) { // sprawdzenie tej samej pozycji
        if (this == other) {
            return true;
        } else if (!(other instanceof Vector2d)) {
            return false;
        } else {
            return this.x == ((Vector2d) other).x && this.y == ((Vector2d) other).y;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}