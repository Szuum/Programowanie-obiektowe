//package agh.ics.oop;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Vector;
//
//public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
//
//    protected HashMap<Vector2d, Animal> animals = new HashMap<>();
//
//    @Override
//    public boolean canMoveTo(Vector2d position) {
//        return !(objectAt(position) instanceof Animal);
//    }
//
//    @Override
//    public boolean place(Animal animal) {
//        Vector2d position = animal.getPosition();
//        if (canMoveTo(position)) {
//            animals.put(position, animal);
//            animal.addObserver(this);
//            return true;
//        }
//        throw new IllegalArgumentException("Position " + position  + " has been already occupied");
//    }
//
//    @Override
//    public boolean isOccupied(Vector2d position) {
//        return objectAt(position)!= null;
//    }
//
//    @Override
//    public Object objectAt(Vector2d position) {
//        return animals.get(position);
//    }
//
//    @Override
//    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
//        Animal animal = animals.remove(oldPosition);
//        animals.put(newPosition, animal);
//    }
//
//
//    public abstract Vector2d getLowerLeft();
//
//    public abstract Vector2d getUpperRight();
//
//    @Override
//    public String toString() {
//        MapVisualizer visualizer = new MapVisualizer(this);
//        return visualizer.draw(getLowerLeft(), getUpperRight());
//    }
//}
