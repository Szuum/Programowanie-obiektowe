package agh.ics.oop;

public class FullOrder implements INextGen {
    @Override
    public int nextGen(int currentGenIdx, int genomLength) {
        return (currentGenIdx + 1)%genomLength;
    }
}
