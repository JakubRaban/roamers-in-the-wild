package pl.jakubraban.evolutionsimulator.map;

import pl.jakubraban.evolutionsimulator.entities.Animal;
import pl.jakubraban.evolutionsimulator.entities.Plant;
import pl.jakubraban.evolutionsimulator.randomness.Probability;
import pl.jakubraban.evolutionsimulator.randomness.RandomnessHandler;

import java.util.*;
import java.util.stream.Collectors;

public class WorldMap {

    private int xSize, ySize;
    private Set<Animal> animals = new HashSet<>();
    private Map<Position, Plant> plants = new HashMap<>();
    private Map<Position, Biome> biomesAtPositions = new HashMap<>();

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
        List<Position> positionsWithinMap = getAllPositionsWithinMap();
        for(Position position : positionsWithinMap) {
            Biome biomeToAssign = getBiomeForPosition(position);
            biomesAtPositions.put(position, biomeToAssign);
        }
    }

    private Biome getBiomeForPosition(Position position) {
        int mapMiddleX = this.xSize / 2;
        int mapMiddleY = this.ySize / 2;
        if(position.getX() >= mapMiddleX - 5 && position.getX() <= mapMiddleX + 4 && position.getY() >= mapMiddleY - 5 && position.getY() <= mapMiddleY + 4)
            return Biome.JUNGLE;
        else return Biome.STEPPE;
    }

    public boolean containsPosition(Position position) {
        return position.getX() >= 0 && position.getX() >= 0
                && position.getX() < this.getWidth() && position.getX() < this.getHeight();
    }

    public List<Position> getAllPositionsWithinMap() {
        List<Position> allPositions = new LinkedList<>();
        for(int x = 0; x < getWidth(); x++) {
            for(int y = 0; y < getHeight(); y++) {
                allPositions.add(new Position(x, y));
            }
        }
        return allPositions;
    }

    private List<Position> getAllPositionsWithinBiome(Biome biome) {
        return biomesAtPositions.entrySet().stream()
                .filter(entry -> entry.getValue().equals(biome))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public Position randomPositionWithinMap() {
        return RandomnessHandler.randomElementFromList(getAllPositionsWithinMap());
    }

    public Position randomPositionWithinBiome(Biome biome) {
        return RandomnessHandler.randomElementFromList(getAllPositionsWithinBiome(biome));
    }

    public void spawnPlants() {
        for(Biome biome : Biome.values()) {
            List<Position> unoccupiedPositions = getUnoccupiedPositionsInBiome(biome);
            List<Position> pickedPositions = RandomnessHandler.randomNElementsFromList(unoccupiedPositions, biome.plantsSpawnedPerDay);
            for(Position position : pickedPositions) {
                if(biome.getSpawningProbabilityForEach().roulette()) plants.put(position, new Plant());
            }
        }
    }

    private List<Position> getUnoccupiedPositions() {
        List<Position> positionsOfAnimals = animals.stream().map(Animal::getPosition).collect(Collectors.toList());
        return biomesAtPositions.keySet().stream()
                .filter(position -> plants.containsKey(position))
                .filter(positionsOfAnimals::contains)
                .collect(Collectors.toList());
    }

    private List<Position> getUnoccupiedPositionsInBiome(Biome biome) {
        return getUnoccupiedPositions().stream()
                .filter(position -> biomesAtPositions.get(position).equals(biome))
                .collect(Collectors.toList());
    }




    private enum Biome {
        STEPPE(1, new Probability(100)), JUNGLE(1, new Probability(100));

        private final int plantsSpawnedPerDay;
        private final Probability spawningProbabilityForEach;

        Biome(int plantsSpawnedPerDay, Probability spawningProbabilityForEach) {
            this.plantsSpawnedPerDay = plantsSpawnedPerDay;
            this.spawningProbabilityForEach = spawningProbabilityForEach;
        }
    }



}
