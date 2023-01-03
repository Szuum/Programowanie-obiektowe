package Projekt;

public class RandomOrder extends NormalOrder{
    @Override
    protected int nextGen(int genomSize, int idx) {
        int x = (int) (Math.random()*10);
        if (x < 8) {
            return super.nextGen(genomSize, idx); // losowanie nowego idneksu aktynej części genomu
        }
        return (int) (Math.random()*genomSize);
    }
}
