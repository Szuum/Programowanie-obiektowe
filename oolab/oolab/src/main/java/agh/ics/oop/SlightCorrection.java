package agh.ics.oop;

public class SlightCorrection implements Mutation {
    @Override
    public int newGen(int currentGen) {
        int variant = (int) (Math.random()*2);
        int gen;
        if (variant == 0) {
            gen = (currentGen + 7) % 8;
        }
        else {
            gen = (currentGen + 1) % 8;
        }
        return gen;
    }
}