package agh.ics.oop;

import java.util.*;

public class MapBoundary implements IPositionChangeObserver{

    private SortedSet<Vector2d> XSort = new TreeSet<>((v1, v2) -> {
        if (v1.x > v2.x) {
            return 1;
        }
        else if (v1.x < v2.x) {
            return -1;
        }
        else {
            return v1.y - v2.y;
        }
    });

    private SortedSet<Vector2d> YSort = new TreeSet<>((v1, v2) -> {
        if (v1.y > v2.y) {
            return 1;
        }
        else if (v1.y < v2.y) {
            return -1;
        }
        else {
            return v1.x - v2.x;
        }
    });

    protected void addPosition(Vector2d position) {
        XSort.add(position);
        YSort.add(position);
    }

    private void removePosition(Vector2d position) {
        XSort.remove(position);
        YSort.remove(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        removePosition(oldPosition);
        addPosition(newPosition);
    }

    protected Vector2d getLowerLeft() {
        return XSort.first().lowerLeft(YSort.first());
    }

    protected Vector2d getUpperRight() {
        return XSort.last().upperRight(YSort.last());
    }

}
