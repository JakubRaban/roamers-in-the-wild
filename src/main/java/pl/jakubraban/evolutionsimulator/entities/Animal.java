package pl.jakubraban.evolutionsimulator.entities;

import pl.jakubraban.evolutionsimulator.map.MapDirection;
import pl.jakubraban.evolutionsimulator.map.Position;
import pl.jakubraban.evolutionsimulator.randomness.RandomnessHandler;

import java.util.*;

public class Animal {

    public static final int DEFAULT_STARTING_ENERGY = 1000;

    private final String name;
    private Position position;
    private int remainingEnergy;
    private MapDirection facingTowards;
    private final Genes genes;
    private final Species species;
    private final Gender gender;

    private final int daySpawned;
    private final int lifetime = 0;

    public Animal(Position position, int daySpawned) {
        this.daySpawned = daySpawned;
        this.position = position;
        this.name = RandomnessHandler.randomName(8);
        this.remainingEnergy = DEFAULT_STARTING_ENERGY;
        this.facingTowards = RandomnessHandler.randomElementFromList(MapDirection.valueList());
        this.genes = new Genes();
        this.species = Species.UNSPECIFIED;
        this.gender = Gender.UNSPECIFIED;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public int getRemainingEnergy() {
        return remainingEnergy;
    }

    public MapDirection getFacingDirection() {
        return facingTowards;
    }

    public Genes getGenes() {
        return genes;
    }

    private enum Species {
        UNSPECIFIED;
    }

    private enum Gender {
        MALE, FEMALE, UNSPECIFIED;
    }

    private class Genes {

        public static final int MAX_GENE_VALUE = 10;
        private Map<Integer, Integer> genesValues = new HashMap<>();

        public Genes() {
            for(int i = 0; i <= 7; i++) {
                genesValues.put(i, RandomnessHandler.randomIntFromRange(0, 10));
            }
        }

    }
}
