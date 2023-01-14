//package agh.ics.oop; // ten plik wydaje się nieużywany
//
//import java.util.LinkedList;
//import java.util.SortedSet;
//import java.util.TreeSet;
//
//public class ToxicCorpses implements IGrowingGrass{
//
//    private LinkedList<ToxicCorpsesField> occupiedPositions = new LinkedList<>();
//    private SortedSet<ToxicCorpsesField> freePositions = new TreeSet<>((f1, f2) -> {
//        if (f1.deathAnimal > f2.deathAnimal) {
//            return 1;
//        }
//        else {
//            return -1;
//        }
//    });
//    private int width;
//    private int height;
//
//    @Override
//    public Vector2d grownGrass() {
//        int type = (int) (Math.random()*10);
//        int idx;
//        if (type < 8) {
//            idx = (int) (freePositions.size()*0.8);
//        }
//        else {
//            idx = (int) (freePositions.size() - 0.2*freePositions.size());
//        }
////        Vector2d position = freePositions
//    }
//}
