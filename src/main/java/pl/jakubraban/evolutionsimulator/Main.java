package pl.jakubraban.evolutionsimulator;

import pl.jakubraban.evolutionsimulator.map.MapVisualizer;
import pl.jakubraban.evolutionsimulator.map.WorldMap;

public class Main {

    private static World world;
    private static CommandExecutor executor;
    private static Window window;
    private static WorldMap worldMap;
    private static MapVisualizer visualizer;

    public static void main(String ... args) {
        updateWorld(new World());
        world.spawnSingleAnimal();
        while(true) {
            world.simulateNewDay();
            window.setText(visualizer.visualize() + "\n\n" +
                    "Dzień " + world.getDaysElapsed() + "\n" +
                    "Zwierzęta: " + worldMap.getAnimals().size() + "\n" +
                    "Rośliny: " + worldMap.getPlants().size());
            try {
                if(world.getDaysElapsed() % 5000 == 0) {
                    Thread.sleep(5000);
                } else {
                    Thread.sleep(world.getDelay());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateWorld(World newWorld) {
        world = newWorld;
        executor = new CommandExecutor(world);
        if(window == null) window = new Window(executor);
        else window.setExecutor(executor);
        worldMap = world.getWorldMap();
        visualizer = new MapVisualizer(worldMap);
    }

}
