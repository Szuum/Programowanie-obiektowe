package agh.ics.oop;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    public String toString() {
        switch (this) {
            case EAST: return "Wschód";
            case WEST: return "Zachód";
            case NORTH: return "Północ";
            default: return "Południe";
        }
    }

    public MapDirection next() {
        switch (this) {
            case SOUTH: return WEST;
            case NORTH: return EAST;
            case WEST: return NORTH;
            default: return SOUTH;
        }
    }
    public MapDirection previous() {
        switch (this) {
            case WEST: return SOUTH;
            case NORTH: return WEST;
            case SOUTH: return EAST;
            default: return NORTH;
        }
    }

    public Vector2d toUnitVector() {
        switch (this) {
            case SOUTH: return new Vector2d(0, -1);
            case NORTH: return new Vector2d(0, 1);
            case EAST: return new Vector2d(1, 0);
            default: return new Vector2d(-1, 0);
        }
    }
}
