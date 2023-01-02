package Projekt;

public class NormalOrder {
    protected int nextGen(int genomSize, int idx) {
        return (idx + 1)%genomSize;
    }
}
