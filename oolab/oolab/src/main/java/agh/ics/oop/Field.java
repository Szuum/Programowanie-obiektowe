package agh.ics.oop;

import java.util.SortedSet;
import java.util.TreeSet;

public class Field {

    protected int animlasDeadCnt = 0;

    protected TreeSet<Animal> animalsSet = new TreeSet<>((a1, a2) -> {
        if (a1.energy != a2.energy) {
            return a1.energy - a2.energy;
        } else {
            if (a1.daysAlive > a2.daysAlive) {
                return 1;
            } else if (a1.daysAlive < a2.daysAlive) {
                return -1;
            } else {
                if (a1.numbereOfChildren > a2.numbereOfChildren) {
                    return 1;
                } else { // wybór w dowolny sposób, więc możnatu dać else
                    return -1;
                }
            }
        }
    });

    protected void removeAnimal(Animal animal) {
        animalsSet.remove(animal);
    }
}
