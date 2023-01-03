package Projekt;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST,
    NORTHWEST,
    NORTHEAST,
    SOUTHWEST,
    SOUTHEAST;
    public MapDirection next() {
        return switch (this) {
            case NORTH -> NORTHEAST;
            case NORTHWEST -> EAST;
            case EAST -> SOUTHEAST;
            case SOUTHEAST -> SOUTH;
            case SOUTH -> SOUTHWEST;
            case SOUTHWEST -> WEST;
            case WEST -> NORTHWEST;
            case NORTHEAST -> NORTH;
        };
    }
    public Vector2d toUnitVector() {
        return switch (this) {
            case SOUTH -> new Vector2d(0, -1);
            case NORTH -> new Vector2d(0, 1);
            case EAST -> new Vector2d(1, 0);
            case WEST -> new Vector2d(-1, 0);
            case NORTHEAST -> new Vector2d(1, 1);
            case NORTHWEST -> new Vector2d(-1, 1);
            case SOUTHEAST -> new Vector2d(1, -1);
            case SOUTHWEST -> new Vector2d(-1, -1);
        };
    }

    public MapDirection getDirection(int idx) {
        return switch (idx) {
            case 1 -> NORTH;
            case 2 -> NORTHEAST;
            case 3 -> EAST;
            case 4 -> SOUTHEAST;
            case 5 -> SOUTH;
            case 6 -> SOUTHWEST;
            case 7 -> WEST;
            default -> NORTHWEST; // case 8
        };
    }
}
