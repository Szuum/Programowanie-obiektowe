package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {

    public MoveDirection[] moves;
    public IWorldMap map;
    public Vector2d[] positions;
    public List<Animal> animals = new ArrayList<>();

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions) {
        this.moves = moves;
        this.map = map;
        this.positions = positions;
    }

    public int addAnimals() {
        int cnt = 0;
        for (int i = 0 ; i < positions.length ; i++) {
            Animal animal = new Animal(map, positions[i]);
            if (map.place(animal)) {
                animals.add(animal);
                cnt++;
            }
        }
        return cnt;
    }

    @Override
    public void run() {
        int cnt = addAnimals();
        for (int i = 0 ; i < moves.length ; i++) {
            animals.get(i%cnt).move(moves[i]);
        }
    }

    List<Animal> getAnimals() {
        return this.animals;
    }
}
