package pl.jakubraban.evolutionsimulator;

import pl.jakubraban.evolutionsimulator.map.WorldMap;

public class World {

    private WorldMap worldMap;
    private int daysElapsed = 0;

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

    public static void main(String ... args) {
        World world = new World();
        Window window = new Window();
        MapVisualizer visualizer = new MapVisualizer(world.getWorldMap());
        world.bringLife();
        // for(int i = 0; i < 1000; i++) world.simulateNewDay();
        while(true) {
            world.simulateNewDay();
            window.setText(visualizer.visualize() + "\n\nDzień " + world.daysElapsed);
            try {
                if(world.daysElapsed % 5000 == 0) Thread.sleep(5000);
                else Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
