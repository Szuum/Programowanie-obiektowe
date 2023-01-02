package Projekt;

import javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class Field {

    protected ArrayList<Animal> animalsArray = new ArrayList<>();

    protected Animal getStrongestAnimal() {
        sort();
        return animalsArray.get(animalsArray.size() - 1);
    }

    protected Animal getSecondAnimal() {
        sort();
        return animalsArray.get(animalsArray.size() - 2);
    }

    protected void sort() {
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
