package Projekt;

public class RandomGen implements IMutation {
    @Override
    public int newGen(int oldGen) {
        int newGen = (int) (Math.random()*8);
        while (newGen == oldGen) { // zmiana genu aż nie stworzy się nowy
            newGen = (int) (Math.random()*8);
        }
        return newGen;
    }
}
