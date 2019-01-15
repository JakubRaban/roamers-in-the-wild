package pl.jakubraban.evolutionsimulator;

import pl.jakubraban.evolutionsimulator.map.WorldMap;

public class World {

    private WorldMap worldMap;
    private int daysElapsed = 0;
    private static int delay = 100;

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

    public void bringLife() {
        worldMap.addAnimal();
    }

    public WorldMap getWorldMap() {
        return worldMap;
    }

    public static void setDelay(int newDelay) {
        delay = newDelay;
    }

    public static void main(String ... args) {
        World world = new World();
        Window window = new Window();
        WorldMap worldMap = world.getWorldMap();
        MapVisualizer visualizer = new MapVisualizer(worldMap);
        world.bringLife();
        while(true) {
            world.simulateNewDay();
            window.setText(visualizer.visualize() + "\n\n" +
                    "Dzień " + world.daysElapsed + "\n" +
                    "Zwierzęta: " + worldMap.getAnimals().size() + "\n" +
                    "Rośliny: " + worldMap.getPlants().size());
            try {
                if(world.daysElapsed % 5000 == 0) {
                    Thread.sleep(5000);
                } else {
                    Thread.sleep(delay);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
