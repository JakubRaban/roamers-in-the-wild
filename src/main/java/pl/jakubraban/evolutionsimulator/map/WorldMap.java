package pl.jakubraban.evolutionsimulator.map;

import pl.jakubraban.evolutionsimulator.entities.Animal;
import pl.jakubraban.evolutionsimulator.entities.Plant;

import java.util.*;

public class WorldMap {

    private int xSize, ySize;
    private Set<Animal> animals = new HashSet<>();
    private Map<Position, Plant> plants = new HashMap<>();
    private Map<Position, Biome> biomes = new HashMap<>();

    public int getWidth() {
        return xSize;
    }

    public int getHeight() {
        return ySize;
    }

    public WorldMap(int width, int height) {
        if(width < 10 || height < 10) throw new IllegalArgumentException("Map would be too small");
        this.xSize = width;
        this.ySize = height;
    }

    public boolean containsPosition(Position position) {
        return position.getX() >= 0 && position.getX() >= 0
                && position.getX() < this.getWidth() && position.getX() < this.getHeight();
    }

    public List<Position> getAllPositionsInside() {
        List<Position> allPositions = new LinkedList<>();
        for(int x = 0; x < getWidth(); x++) {
            for(int y = 0; y < getHeight(); y++) {
                allPositions.add(new Position(x, y));
            }
        }
        return allPositions;
    }
    
    private enum Biome {
        STEPPE, JUNGLE;
    }

}
