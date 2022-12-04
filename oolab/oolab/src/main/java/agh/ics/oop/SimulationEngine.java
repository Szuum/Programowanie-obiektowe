package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import agh.ics.oop.gui.App;

public class SimulationEngine implements IEngine, Runnable {

    public MoveDirection[] moves;
    public IWorldMap map;
    public Vector2d[] positions;
    public List<Animal> animals = new ArrayList<>();
    public App app;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions) {
        this.moves = moves;
        this.map = map;
        this.positions = positions;
    }

    public SimulationEngine(IWorldMap map, Vector2d[] positions, App app) {
        this.map = map;
        this.positions = positions;
        this.app = app;
    }

    public void setDiractions (MoveDirection[] moves) {
        this.moves = moves;
    }

    public int addAnimals() {
        int cnt = 0;
        for (Vector2d position : positions) {
            Animal animal = new Animal(map, position);
            if (map.place(animal)) {
                animals.add(animal);
                if (app != null) {
                    animal.addObserver(app);
                }
                cnt++;
            }
        }
        return cnt;
    }

    @Override
    public void run() {
        int moveDelay = 300;
        int cnt = addAnimals();
        for (int i = 0 ; i < moves.length ; i++) {
            animals.get(i%cnt).move(moves[i]);
            try {
                Thread.sleep(moveDelay);
            }
            catch (InterruptedException exception){
                System.out.println("Przerwano symulację");
            }
        }
    }

    List<Animal> getAnimals() {
        return this.animals;
    }
}
