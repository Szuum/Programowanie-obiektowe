package Projekt;

import java.util.ArrayList;

public class Field {

    protected ArrayList<Animal> animalsArray = new ArrayList<>(); // lista przechowująca zwierzęta na danym polu

    protected Animal getStrongestAnimal() { // najsilniejsze zwierzę na polu
        sort();
        return animalsArray.get(animalsArray.size() - 1);
    }

    protected Animal getSecondAnimal() { // drugie najsilniejsze zwierzę (do rozmnażania)
        sort();
        return animalsArray.get(animalsArray.size() - 2);
    }

    protected void sort() { // sortowanie według zadanych kryteriów
        animalsArray.sort((a1, a2) -> {
            if (a1 == a2) {
                return 0;
            }
            if (a1.energy > a2.energy) {
                return 1;
            }
            else if (a1.energy < a2.energy) {
                return -1;
            }
            else {
                if (a1.age > a2.age) {
                    return 1;
                }
                else if (a1.age < a2.age) {
                    return -1;
                }
                else {
                    return a1.childNumber - a2.childNumber;
                }
            }
        });
    }
}