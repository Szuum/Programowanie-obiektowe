package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.SortedSet;

public class Map implements Runnable { // Runnable? Map nie dziedziczy z AbstractMap
    protected final int width;
    protected final int height;
    protected int day = 0;
    protected final int plusEnergy;
    protected final int lostEnergy;
    protected final int grownGrasses;
    protected final int startEnegry;
    protected final int minEnergyToReproduce;
    protected final int genomSize;
    private final int minNumberMutation;
    private final int maxNumberMutation;
    protected final AbstractMap mapVariant;
    protected final INextGen nextGenVariant;
    protected final Mutation mutationVariant;
    protected final IGrowingGrass growingVariant;
    private int animalCnt = 0;
    protected int grassCnt = 0;
    private int mostPopularGen = 0;
    private int[] genes = {0, 0, 0, 0, 0, 0, 0, 0}; // przydałaby się osobna klasa na genotyp; czy on zawsze ma 8 składowych?
    protected int sumOfEnergy = 0;
    private int sumOfLife = 0;
    private int deadAnimals = 0;
    protected HashMap<Vector2d, Field> animals = new HashMap<>();
    protected HashMap<Vector2d, Grass> grasses = new HashMap<>();
    protected ArrayList<Animal> allAnimals = new ArrayList<>();
    private Simulation engine; // mylące - ma Pan też typ SimulationEngine

    public Map(int width, int height, int plusEnergy, int lostEnergy, int grownGrasses, int startEnegry, int minEnergyToReproduce,
               AbstractMap mapVariant, int genomSize, INextGen nextGenVariant, Mutation mutationVariant, IGrowingGrass growingVariant,
               int minNumberMutation, int maxNumberMutation, Simulation engine) {
        this.width = width;
        this.height = height;
        this.plusEnergy = plusEnergy;
        this.lostEnergy = lostEnergy;
        this.grownGrasses = grownGrasses;
        this.startEnegry = startEnegry;
        this.minEnergyToReproduce = minEnergyToReproduce;
        this.mapVariant = mapVariant;
        this.genomSize = genomSize;
        this.minNumberMutation = minNumberMutation;
        this.maxNumberMutation = maxNumberMutation;
        this.nextGenVariant = nextGenVariant;
        this.mutationVariant = mutationVariant;
        this.growingVariant = growingVariant;
        this.engine = engine;
    }

    public void deleteDeadAnimals() {
        Set<Vector2d> keys = animals.keySet();
        for (Vector2d key : keys) {
            while (!animals.get(key).animalsSet.isEmpty() && animals.get(key).animalsSet.first().energy <= 0) {
                Animal animal = animals.get(key).animalsSet.first();
                animals.get(key).animalsSet.remove(animal);
                deadAnimals++;
                sumOfLife += day;
                animalCnt--;
                animal.deathDay = day;
                deleteGenes(animal);
                allAnimals.remove(animal);
            }
            if (animals.get(key).animalsSet.isEmpty()) {
                animals.remove(key);
            }
        }
    }

    private int findMostPopularGen() {
        int res = 0;
        for (int i = 1; i < 8; i++) {
            if (genes[i] > genes[res]) {
                res = i;
            }
        }
        return res;
    }

    public Vector2d move(Animal animal, Vector2d newPosition) {
        return mapVariant.move(this, animal, newPosition);
    }

    public void multiplication() {
        Set<Vector2d> keys = animals.keySet();
        for (Vector2d key : keys) {
            Animal parent1 = animals.get(key).animalsSet.last();
            if (animals.get(key).animalsSet.size() > 1) {
                Animal parent2 = animals.get(key).animalsSet.lower(parent1);
                if (parent2.energy >= minEnergyToReproduce) { // czy mapa jest właściwym miejscem na to?
                    int totalEnergy = parent1.energy + parent2.energy;
                    int animal1GenesCnt = (int) genomSize * (parent1.energy / totalEnergy);
                    int animal2GenesCnt = genomSize - animal1GenesCnt;
                    int side1 = (int) (Math.random() * 2);
                    Animal child = new Animal(parent1.position, this, 2 * lostEnergy, genomSize);
                    copyGenes(parent1, child, side1, animal1GenesCnt);
                    copyGenes(parent2, child, (side1 + 1) % 2, animal2GenesCnt);
                    int numberOfMutation = (int) (Math.random() * (maxNumberMutation - minNumberMutation + 1));
                    child.mutation(this.mutationVariant, numberOfMutation);
                    place(child);
                    parent1.numbereOfChildren++;
                    parent2.numbereOfChildren++;
                }
            }
        }
    }

    private void copyGenes(Animal parent, Animal child, int side, int quantity) { // czemu to robi mapa?
        if (side == 0) {
            for (int i = 0; i < quantity; i++) {  // https://docs.oracle.com/javase/7/docs/api/java/lang/System.html#arraycopy(java.lang.Object,%20int,%20java.lang.Object,%20int,%20int)
                child.genes[i] = parent.genes[i];
            }
        } else {
            for (int i = 0; i < quantity; i++) {
                child.genes[genomSize - i - 1] = parent.genes[genomSize - i - 1];
            }
        }
    }
//
//    public boolean canMoveTo(Vector2d position) {
//        return !(objectAt(position) instanceof Animal);
//    }

    public void place(Animal animal) {
        placeAnimalOnField(animal);
        animalCnt += 1;
        sumOfEnergy += animal.energy;
        addGanes(animal);
    }

    private void addGanes(Animal animal) {
        for (int gen : animal.genes) {
            genes[gen]++;
        }
        mostPopularGen = findMostPopularGen();
    }

    private void deleteGenes(Animal animal) {
        for (int gen : animal.genes) {
            genes[gen]--;
        }
        mostPopularGen = findMostPopularGen();
    }

    private void placeAnimalOnField(Animal animal) {
        Vector2d position = animal.getPosition();
        if (animals.containsKey(position)) {
            animals.get(position).animalsSet.add(animal);
        } else {
            Field field = new Field();
            field.animalsSet.add(animal);
            animals.put(position, field);
        }
        allAnimals.add(animal);
    }

    public void consumption() {
        mapVariant.consumption(this); // czemu konsumpcja jest delegowana? tu akurat nie ma wariantów
    }
//    public boolean isOccupied(Vector2d position) {
//        return objectAt(position)!= null;
//    }

//    public Object objectAt(Vector2d position) {
//        return animals.get(position);
//    }

    public void positionChanged(Vector2d oldPosition, Animal animal) {
        Field field = animals.get(oldPosition);
        field.removeAnimal(animal);
        if (animals.get(oldPosition).animalsSet.isEmpty()) {
            animals.remove(oldPosition);
        }
        placeAnimalOnField(animal);
        engine.mapChanged();
    }

    protected Object objectAt(Vector2d position) {
        if (animals.containsKey(position)) {
            return animals.get(position).animalsSet.last();
        } else if (grasses.containsKey(position)) {
            return grasses.get(position);
        } else {
            return null;
        }
    }

    protected void addGrass() {
        Vector2d position = growingVariant.grownGrass();
        grasses.put(position, new Grass(position));
    }
    // analiza granic mapy TODO

    protected void moveAllAnimals() { // po co ta metoda, skoro ruch zwierząt jest w run?
        for (Animal animal : allAnimals) {
            animal.move();
        }
    }


    @Override
    public void run() {
        for (Animal animal : allAnimals) {
            animal.move();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace(); // to nie jest dobra obsługa wyjątku
            }
        }

    }
}
