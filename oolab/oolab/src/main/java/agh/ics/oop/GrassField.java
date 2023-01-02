//package agh.ics.oop;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class GrassField extends AbstractWorldMap {
//
//    private int number;
//    protected HashMap<Vector2d, Grass> grasses = new HashMap<>();
//    private MapBoundary boundary = new MapBoundary();
//
//    public GrassField(int number) {
//        this.number = number;
//        int maxPosition = (int) Math.sqrt(this.number * 10);
//        int i = 0;
//        while (i < number) {
//            Vector2d position = new Vector2d((int) (Math.random() * maxPosition), (int) (Math.random() * maxPosition));
//            if (!grassOnPlace(position)) {
//                grasses.put(position, new Grass(position));
//                i++;
//                boundary.addPosition(position);
//            }
//        }
//    }
//
//    private boolean grassOnPlace(Vector2d position) {
//        return grasses.containsKey(position);
//    }
//
//    @Override
//    public Object objectAt(Vector2d position) {
//        if (super.objectAt(position) != null) {
//            return super.objectAt(position);
//        }
//        return grasses.get(position);
//    }
//
//    @Override
//    public boolean place(Animal animal) {
//        if (super.place(animal)) {
//            boundary.addPosition(animal.getPosition());
//            return true;
//        }
//        return false;
//    }
//
//    public Vector2d getLowerLeft() {
//        return boundary.getLowerLeft();
//    }
//
//    public Vector2d getUpperRight() {
//        return boundary.getUpperRight();
//    }
//
//    @Override
//    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
//        super.positionChanged(oldPosition, newPosition);
//        boundary.positionChanged(oldPosition, newPosition);
//        if (grasses.containsKey(oldPosition)) {
//            boundary.addPosition(oldPosition);
//        }
//    }
//}
