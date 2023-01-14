package Projekt;

import java.util.ArrayList;

public class Animal implements IMapElement {
    protected Vector2d position;
    private MapDirection orient = MapDirection.NORTH;
    protected int energy;
    protected int childNumber = 0;
    protected int age = 1;
    protected int genomSize;
    protected int[] genom;
    private final IMutation mutationVariant;
    private final NormalOrder nextGenVariant;
    private int activeGenIdx;
    protected int grassesEaten = 0;
    protected int deathDay;
    private final int minMutation;
    private final int maxMutation;
    private final int energyToMultiplication;
    protected int lostEnergy;
    private final AbstractMap map;
    private final Simulation engine;

    public Animal(Vector2d position, int energy, int genomSize, IMutation mutationVariant, NormalOrder nextGenVariant, int minMutation,
                  int maxMutation, int energyToMultiplication, int lostEnergy, AbstractMap map, Simulation engine) {
        this.position = position;
        this.energy = energy;
        this.genomSize = genomSize;
        this.mutationVariant = mutationVariant;
        this.nextGenVariant = nextGenVariant;
        this.minMutation = minMutation;
        this.maxMutation = maxMutation;
        this.energyToMultiplication = energyToMultiplication;
        this.lostEnergy = lostEnergy;
        this.map = map;
        this.engine = engine;
        genom = new int[genomSize];
        createGenom();
        activeGenIdx = (int) (Math.random() * genomSize);
        int orientIdx = (int) (Math.random() * 8);
        orient = orient.getDirection(orientIdx);
    }

    protected void copyGenes(Animal parent, int side, int number) { //kopiowanie genów od rodzica
        if (side == 0) {
            if (number >= 0) System.arraycopy(parent.genom, 0, genom, 0, number);
        } else {
            for (int i = 0; i < number; i++) {
                genom[genomSize - i - 1] = parent.genom[genomSize - i - 1];
            }
        }
    }

    protected void mutation() { // mutacja genów
        int numberOfMutation = (int) (Math.random() * (maxMutation - minMutation)) + minMutation;
        ArrayList<Integer> changedGenes = new ArrayList<>();
        for (int i = 0; i < numberOfMutation; i++) {
            int genIdx = (int) (Math.random() * genomSize);
            while (changedGenes.contains(genIdx)) {
                genIdx = (int) (Math.random() * genomSize);
            }
            int newGen = mutationVariant.newGen(genom[genIdx]);
            genom[genIdx] = newGen;
            changedGenes.add(genIdx);
        }
    }

    private void createGenom() { // stworzenie genomu dla nowego zwierzęcia
        for (int i = 0; i < genomSize; i++) {
            genom[i] = (int) (Math.random() * 8);
        }
    }

    private void changeOrient() { // obrót zwierzęcia
        for (int i = 0; i < genom[activeGenIdx]; i++) {
            orient = orient.next();
        }
    }

    protected void oppositeOrient() { // zmiana kierunku w przypadku uderzenia w granicę (mapa Globe)
        orient = switch (this.orient) {
            case NORTH -> MapDirection.SOUTH;
            case NORTHEAST -> MapDirection.SOUTHWEST;
            case EAST -> MapDirection.WEST;
            case SOUTHEAST -> MapDirection.NORTHWEST;
            case SOUTH -> MapDirection.NORTH;
            case SOUTHWEST -> MapDirection.NORTHEAST;
            case WEST -> MapDirection.EAST;
            case NORTHWEST -> MapDirection.SOUTHEAST;
        };
    }

    protected boolean move() { // poruszanie się zwierzęcia
        changeOrient(); // obrót
        Vector2d newPosition = map.newPosition(this, position.add(orient.toUnitVector())); // pozycja po ruchu
        energy--;
        age++;
        if (!(newPosition.equals(position))) { // zwierzę ruczyło się
            engine.updateFields(this, this.position, newPosition);
            position = newPosition;
            return true;
        }
        engine.updateFields(this, this.position, this.position); // zwierzę nie ruszyło się
        return false;
    }

    @Override
    public String getImagePath() {
        return "src/main/resources/animal.png";
    }

    @Override
    public String getData() {
        return Integer.toString(this.energy);
    }
}