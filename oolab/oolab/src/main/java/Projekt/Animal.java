package Projekt;

import java.util.ArrayList;

public class Animal implements IMapElement{
    protected Vector2d position;
    private MapDirection orient = MapDirection.NORTH;
    protected int energy;
    protected int childNumber = 0;
    protected int age = 1;
    protected int genomSize;
    protected int[] genom;
    private IMutation mutationVariant;
    private NormalOrder nextGenVariant;
    private int activeGenIdx;
    protected int grassesEaten = 0;
    protected int deathDay;
    private int minMutation;
    private int maxMutation;
    private int energyToMultiplication;
    protected int lostEnergy;
    private AbstractMap map;
    private Simulation engine;

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
        activeGenIdx = (int) (Math.random()*genomSize);
        int orientIdx = (int) (Math.random()*8);
        orient = orient.getDirection(orientIdx);
    }

    protected void copyGenes(Animal parent, int side, int number) {
        if (side == 0) {
            for (int i = 0 ; i < number ; i++) {
                genom[i] = parent.genom[i];
            }
        }
        else {
            for (int i = 0 ; i < number ; i++) {
                genom[genomSize - i - 1] = parent.genom[genomSize - i - 1];
            }
        }
    }

    protected void mutation() {
        int numberOfMutation = (int) (Math.random()*(maxMutation - minMutation)) + minMutation;
        ArrayList<Integer> changedGenes = new ArrayList<>();
        for (int i = 0 ; i < numberOfMutation ; i++) {
            int genIdx = (int) (Math.random()*genomSize);
            while (changedGenes.contains(genIdx)) {
                genIdx = (int) (Math.random()*genomSize);
            }
            int newGen = mutationVariant.newGen(genom[genIdx]);
            genom[genIdx] = newGen;
            changedGenes.add(genIdx);
        }
    }
    private void createGenom() {
        for (int i = 0 ; i < genomSize ; i++) {
            genom[i] = (int) (Math.random()*8);
        }
    }

    private void changeOrient() {
        for (int i = 0 ; i < genom[activeGenIdx] ; i++) {
            orient = orient.next();
        }
    }

    protected void oppositeOrient() {
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

    protected boolean move() {
        changeOrient();
        Vector2d newPosition = map.newPosition(this, position.add(orient.toUnitVector()));
        energy--;
        age++;
        if (!(newPosition.equals(position))) {
            engine.updateFields(this, this.position, newPosition);
            position = newPosition;
            return true;
        }
        engine.updateFields(this, this.position, this.position);
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
