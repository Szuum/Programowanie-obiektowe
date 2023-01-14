package Projekt;

public class SlightCorrection implements IMutation {
    @Override
    public int newGen(int oldGen) {
        int x = (int) (Math.random() * 2);
        if (x == 0) {
            return (oldGen + 7) % 8; // zmiana genu o 1 w górę
        }
        return (oldGen + 1) % 8; // zmiana genu o 1 w dół
    }
}