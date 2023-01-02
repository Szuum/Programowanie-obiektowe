package agh.ics.oop;

public class RandomGen implements Mutation{
    @Override
    public int newGen(int currentGen) {
        int gen = (int) (Math.random()*8);
        while (gen == currentGen) {
            gen = (int) (Math.random()*8);
        }
        return gen;
    }
}
