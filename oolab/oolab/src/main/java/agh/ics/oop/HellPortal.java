package agh.ics.oop;

import java.util.HashMap;
import java.util.Set;

public class HellPortal extends AbstractMap {

    @Override
    public Vector2d move(Map map, Animal animal, Vector2d newPosition) {
        Vector2d oldPosition = animal.getPosition();
        if (!super.move(map, animal, newPosition).equals(oldPosition)) {
            return super.move(map, animal, newPosition);
        }
        int x = (int) (Math.random() * (map.width + 1));
        int y = (int) (Math.random() * (map.height + 1));
        if (animal.energy <= map.lostEnergy) {
            map.sumOfEnergy -= animal.energy - 1; // czy HellPortal powinien się tym zajmować?
        } else {
            map.sumOfEnergy -= map.lostEnergy;
        }
        animal.energy -= map.lostEnergy;
        return new Vector2d(x, y);
    }

    @Override
    public void consumption(Map map) {
        Set<Vector2d> keys = map.grasses.keySet();
        for (Vector2d key : keys) {
            if (map.animals.containsKey(key)) {
                Animal animal = map.animals.get(key).animalsSet.last();
                if (animal.energy >= 0) {
                    map.grasses.remove(key);
                    map.growingVariant.deleteGrass(key);
                    animal.energy += map.plusEnergy;
                    animal.eatenGrass++;
                    map.grassCnt--;
                }
            }
        }
    }
}
