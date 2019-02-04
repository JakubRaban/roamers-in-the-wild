package pl.jakubraban.evolutionsimulator.map;

import pl.jakubraban.evolutionsimulator.entities.Animal;

import java.util.Set;
import java.util.stream.Collectors;

public class MapVisualizer {

    WorldMap worldMap;

    public MapVisualizer(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public String visualize() {
        Set<Position> positionsOfAnimals = worldMap.getAnimals().stream().map(Animal::getPosition).collect(Collectors.toSet());
        Set<Position> positionsOfPlants = worldMap.getPlants().keySet();
        StringBuilder mapVisualization = new StringBuilder();
        for(int y = worldMap.getHeight() - 1; y >= 0; y--) {
            for(int x = 0; x < worldMap.getWidth(); x++) {
                Position currentPosition = new Position(x, y);
                if(positionsOfAnimals.contains(currentPosition)) mapVisualization.append("M");
                else if(positionsOfPlants.contains(currentPosition)) mapVisualization.append("*");
                else mapVisualization.append(".");
            }
            mapVisualization.append("\n");
        }
        return mapVisualization.toString();
    }

}
