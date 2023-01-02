package Projekt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Simulation implements Runnable{
    protected final int width;
    protected final int height;
    protected final int startGrass;
    protected final int plusEnergy;
    protected final int grownGrass;
    protected final AbstractMap mapVariant;
    protected final int startAnimals;
    protected final int startEnergy;
    protected final int energyToMultiplication;
    protected final int lostEnergy;
    protected final int minMutation;
    protected final int maxMutation;
    protected final IMutation mutationVariant;
    protected final int genomSize;
    protected final NormalOrder nextGenVariant;
    protected IGrowVariant growVariant;
    private ArrayList<Animal> animals = new ArrayList<>();
    private HashMap<Vector2d, Grass> grasses = new HashMap<>();
    private HashMap<Vector2d, Field> animalsOnField = new HashMap<>();
    private App app;
    private HashMap<int[], Integer> genoms = new HashMap<int[], Integer>();

    public Simulation(int width, int height, int startGrass, int plusEnergy, int grownGrass, AbstractMap mapVariant, int startAnimals,
                      int startEnergy, int energyToMultiplication, int lostEnergy, int minMutation, int maxMutation, IMutation mutationVariant,
                      int genomSize, NormalOrder nextGenVariant, App app, IGrowVariant growVariant) {
        this.width = width;
        this.height = height;
        this.startGrass = startGrass;
        this.plusEnergy = plusEnergy;
        this.grownGrass = grownGrass;
        this.mapVariant = mapVariant;
        this.startAnimals = startAnimals;
        this.startEnergy = startEnergy;
        this.energyToMultiplication = energyToMultiplication;
        this.lostEnergy = lostEnergy;
        this.minMutation = minMutation;
        this.maxMutation = maxMutation;
        this.mutationVariant = mutationVariant;
        this.genomSize = genomSize;
        this.nextGenVariant = nextGenVariant;
        this.app = app;
        this.growVariant = growVariant;

        for (int i = 0 ; i < startAnimals ; i++) {
            int x = (int) (Math.random()*width);
            int y = (int) (Math.random()*height);
            Animal animal = new Animal(new Vector2d(x, y), startEnergy, genomSize, mutationVariant, nextGenVariant, minMutation,
                    maxMutation, energyToMultiplication, lostEnergy, mapVariant, this);
            mapVariant.place(animal);
            animals.add(animal);
            if (genoms.containsKey(animal.genom)) {
                int cnt = genoms.remove(animal.genom);
                genoms.put(animal.genom, cnt+1);
            }
            else {
                genoms.put(animal.genom, 1);
            }
            app.mostPopularGenom = findPopularGenom();
            app.animalCnt++;
            app.totalEnergy += startEnergy;
            putAnimalOnField(animal, animal.position);
        }
        for (int i = 0 ; i < startGrass ; i++) {
            if (growVariant.canAddGrass()) {
                Vector2d position = growVariant.addGrass();
                grasses.put(position, new Grass(position, plusEnergy));
                app.grassCnt++;
            }
            else {
                break;
            }
        }
    }

    private int[] findPopularGenom() {
        Set<int[]> gonomsSet = genoms.keySet();
        int cnt = 0;
        int[] popularGenom = new int[app.genomSize];
        for (int[] genom : gonomsSet) {
            if (genoms.get(genom) > cnt) {
                popularGenom = genom;
            }
        }
        return popularGenom;
    }

    private void putAnimalOnField(Animal animal, Vector2d position) {
        if (animalsOnField.containsKey(position)) {
            animalsOnField.get(position).animalsArray.add(animal);
        }
        else {
            animalsOnField.put(position, new Field());
            animalsOnField.get(position).animalsArray.add(animal);
        }
    }

    private void removeAnimalFromField(Animal animal, Vector2d position) {
        animalsOnField.get(position).animalsArray.remove(animal);
        if (animalsOnField.get(position).animalsArray.isEmpty()) {
            animalsOnField.remove(position);
        }
    }

    protected void updateFields(Animal animal, Vector2d oldPosition, Vector2d newPosition) {
        removeAnimalFromField(animal, oldPosition);
        putAnimalOnField(animal, newPosition);
    }

    protected IMapElement objectAt(Vector2d position) {
        if (animalsOnField.containsKey(position)) {
            app.freeFieldCnt--;
            return animalsOnField.get(position).getStrongestAnimal();
        }
        else if (grasses.containsKey(position)){
            app.freeFieldCnt--;
            return grasses.get(position);
        }
        return null;
    }

    private boolean consumption(Grass grass) {
        Vector2d position = grass.position;
        if (animalsOnField.containsKey(position)) {
            growVariant.grassEaten(position);
            Animal animal = animalsOnField.get(position).getStrongestAnimal();
            animal.energy += plusEnergy;
            return true;
        }
        return false;
    }

    private Object multiplication(Animal parent1, Animal parent2) {
        if (parent1.energy >= energyToMultiplication && parent2.energy >= energyToMultiplication) {
            int totalEnergy = parent1.energy + parent2.energy;
            int genesFromP1Cnt = (int) (genomSize*parent1.energy/totalEnergy);
            int genesFromP2Cnt = genomSize - genesFromP1Cnt;
            int side1 = (int) (Math.random()*2);
            Animal child = new Animal(parent1.position, 2*lostEnergy, genomSize, mutationVariant, nextGenVariant, minMutation,
                    maxMutation, energyToMultiplication, lostEnergy, mapVariant, this);
            child.copyGenes(parent1, side1, genesFromP1Cnt);
            child.copyGenes(parent2, (side1 + 1)%2, genesFromP2Cnt);
            child.mutation();
            parent1.energy -= lostEnergy;
            parent2.energy -= lostEnergy;
            parent1.childNumber++;
            parent2.childNumber++;
            mapVariant.place(child);
            animals.add(child);
            return child;
        }
        return null;
    }

    private void deleteDeadAnimals(ArrayList<Animal> deadAnimlas) {
        for (Animal animal : deadAnimlas) {
            animals.remove(animal);
        }
    }

    private void deleteEatenGrasses(ArrayList<Vector2d> eatenGrasses) {
        for (Vector2d position : eatenGrasses) {
            grasses.remove(position);
        }
    }

    private void updateParentsFields(ArrayList<Animal> newParent) {
        for (Animal parent : newParent) {
            updateFields(parent, parent.position, parent.position);
        }
    }

    private void addNewbornToFields(ArrayList<Animal> newborns) {
        for (Animal child : newborns) {
            putAnimalOnField(child, child.position);
        }
    }

    @Override
    public void run() {
        while (!app.engineThread.isInterrupted()) {

            ArrayList<Animal> deadAnimals = new ArrayList<>();
            for (Animal animal : animals) {
                if (mapVariant.deleteAnimal(animal)) {
                    deadAnimals.add(animal);
                    removeAnimalFromField(animal, animal.position);
                    growVariant.animalDead(animal.position);
                    int cnt = genoms.remove(animal.genom);
                    genoms.put(animal.genom, cnt++);
                    app.mostPopularGenom = findPopularGenom();
                    app.deadAnimal++;
                    app.animalCnt--;
                    app.totalLife += animal.age;
                }
            }
            app.mapChanged();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            deleteDeadAnimals(deadAnimals);

            for (Animal animal : animals) {
                app.totalEnergy--;
                animal.move();
            }
            app.mapChanged();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Set<Vector2d> keys = grasses.keySet();
            ArrayList<Vector2d> eatenGrasses = new ArrayList<>();
            for (Vector2d key : keys) {
                if (consumption(grasses.get(key))) {
                    app.totalEnergy += plusEnergy;
                    app.grassCnt--;
                    app.mapChanged();
                    eatenGrasses.add(key);
                }
            }
            deleteEatenGrasses(eatenGrasses);
            app.mapChanged();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Set<Vector2d> positions = animalsOnField.keySet();
            ArrayList<Animal> newborns = new ArrayList<>();
            ArrayList<Animal> newParents = new ArrayList<>();
            for (Vector2d position : positions) {
                if (animalsOnField.get(position).animalsArray.size() > 1) {
                    Animal parent1 = animalsOnField.get(position).getStrongestAnimal();
                    Animal parent2 = animalsOnField.get(position).getSecondAnimal();
                    Animal child = (Animal) multiplication(parent1, parent2);
                    if (child != null) {
                        app.animalCnt++;
                        if (genoms.containsKey(child.genom)) {
                            int cnt = genoms.remove(child.genom);
                            genoms.put(child.genom, cnt+1);
                        }
                        else {
                            genoms.put(child.genom, 0);
                        }
                        app.mostPopularGenom = findPopularGenom();

                        newborns.add(child);
                        newParents.add(parent1);
                        newParents.add(parent2);
                    }
                }
                app.mapChanged();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            updateParentsFields(newParents);
            addNewbornToFields(newborns);

            for (int i = 0 ; i < grownGrass ; i++) {
                if (growVariant.canAddGrass()) {
                    Vector2d position = growVariant.addGrass();
                    grasses.put(position, new Grass(position, plusEnergy));
                    app.grassCnt++;
                }
                else {
                    break;
                }
            }
            app.mapChanged();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
