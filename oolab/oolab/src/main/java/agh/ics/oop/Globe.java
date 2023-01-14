package agh.ics.oop;

import java.util.HashMap;
import java.util.Set;

public class Globe extends AbstractMap {

    @Override // odwołania do x i y -> do zastanowienia/poprawy
    public Vector2d move(Map map, Animal animal, Vector2d newPosition) {
        Vector2d oldPosition = animal.getPosition();
        if (!super.move(map, animal, newPosition).equals(oldPosition)) {
            return super.move(map, animal, newPosition);
        }
        if (newPosition.x >= 0 && newPosition.x < map.width) {
            animal.changeDirection();
            return oldPosition;
        }
        if (newPosition.y < 0) {
            return new Vector2d((newPosition.x + map.width) % map.width, 0);
        } else if (newPosition.y > map.height) {
            return new Vector2d((newPosition.x + map.width) % map.width, map.height);
        } else {
            return new Vector2d((newPosition.x + map.width) % map.width, newPosition.y);
        }
    }

    @Override
    public void consumption(Map map) {
        Set<Vector2d> keys = map.grasses.keySet();
        for (Vector2d key : keys) {
            if (map.animals.containsKey(key)) {
                Animal animal = map.animals.get(key).animalsSet.last();
                map.grasses.remove(key);
                map.growingVariant.deleteGrass(key);
                animal.energy += map.plusEnergy;
                animal.eatenGrass++;
                map.grassCnt--;
            }
        }
    }
}
