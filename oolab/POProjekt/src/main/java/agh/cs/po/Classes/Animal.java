package agh.cs.po.Classes;

import java.util.ArrayList;
import java.util.List;

public class Animal {
    private MapDirection orient;
    private Vector2d position = new Vector2d(2, 2);
    public IWorldMap map;
    private List<IPositionChangeObserver> observers = new ArrayList<>();
}
