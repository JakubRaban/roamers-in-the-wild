package pl.jakubraban.evolutionsimulator;

import pl.jakubraban.evolutionsimulator.map.WorldMap;

public class World {

    private WorldMap worldMap;
    private int daysElapsed = 0;

    public World() {
        worldMap = new WorldMap(100, 30);
    }

    public void simulateNewDay() {
        worldMap.removeDeadAnimals();
        worldMap.spawnPlants();
        worldMap.moveAnimals();
        worldMap.bringAnimalsBackToMap();
        worldMap.feedAnimals();
        worldMap.reproduceAnimals(daysElapsed);
        daysElapsed++;
    }

}
