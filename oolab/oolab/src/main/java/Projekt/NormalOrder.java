package Projekt;

public class NormalOrder {
    protected int nextGen(int genomSize, int idx) { // zwrócenie kolejnego denu w kolejności
        return (idx + 1)%genomSize;
    }
}