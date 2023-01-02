package agh.ics.oop;

public class CrazyChange implements INextGen{
    @Override
    public int nextGen(int currentGenIdx, int genomLength) {
        int variant = (int) (Math.random()*10);
        if (variant%10 < 8) {
            return (currentGenIdx + 1)%genomLength;
        }
        else {
            int nextGenIdx = (int) (Math.random()*(genomLength + 1));
            while (nextGenIdx == currentGenIdx) {
                nextGenIdx = (int) (Math.random()*(genomLength + 1));
            }
            return nextGenIdx;
        }
    }
}
