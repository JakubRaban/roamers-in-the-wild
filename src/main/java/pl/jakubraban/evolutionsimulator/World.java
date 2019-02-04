package pl.jakubraban.evolutionsimulator;

import pl.jakubraban.evolutionsimulator.map.WorldMap;

public class World {

    private WorldMap worldMap;
    private int daysElapsed = 0;
    private int delay = 100;

    public World() {
        worldMap = new WorldMap(100,30);
    }

    public void simulateNewDay() {
        worldMap.removeDeadAnimals();
        worldMap.spawnPlants();
        worldMap.moveAnimals();
        worldMap.bringAnimalsBackToMap();
        worldMap.feedAnimals();
        worldMap.reproduceAnimals();
        daysElapsed++;
    }

    public int getDaysElapsed() {
        return daysElapsed;
    }

    public void spawnSingleAnimal() {
        worldMap.addAnimal();
    }

    public WorldMap getWorldMap() {
        return worldMap;
    }

    public void setDelay(int newDelay) {
        delay = newDelay;
    }

    public int getDelay() {
        return delay;
    }

    public void restartGame() {
        worldMap = new WorldMap(100,30);
        daysElapsed = 0;
        spawnSingleAnimal();
        Main.updateWorld(this);
    }

}
