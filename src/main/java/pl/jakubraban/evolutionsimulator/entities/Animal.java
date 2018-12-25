package pl.jakubraban.evolutionsimulator.entities;

import pl.jakubraban.evolutionsimulator.map.MapDirection;
import pl.jakubraban.evolutionsimulator.map.Position;
import pl.jakubraban.evolutionsimulator.randomness.RandomnessHandler;

import java.util.*;

public class Animal {

    private final String name;
    private Position position;
    private int remainingEnergy;
    private MapDirection facingTowards;
    private final Gene gene;
    private final Species species;
    private final Gender gender;

    private final int daySpawned;
    private int lifetime;

    private enum Species {
        UNSPECIFIED;
    }

    private enum Gender {
        MALE, FEMALE, UNSPECIFIED;
    }

    private class Gene {

        public static final int MAX_GENE_VALUE = 10;
        private Map<Integer, Integer> geneValues = new HashMap<>();

        public Gene() {
            for(int i = 0; i <= 7; i++) {
                geneValues.put(i, RandomnessHandler.randomIntFromRange(0, 10));
            }
        }

    }
}
