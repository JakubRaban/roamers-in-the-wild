package pl.jakubraban.evolutionsimulator.entities;

import pl.jakubraban.evolutionsimulator.map.MapDirection;
import pl.jakubraban.evolutionsimulator.map.MoveDirection;
import pl.jakubraban.evolutionsimulator.randomness.RandomnessHandler;

import java.util.HashMap;
import java.util.Map;

public class Animal {

    public static final int DEFAULT_STARTING_ENERGY = 1000;

    private String name;
    private int remainingEnergy;
    private MapDirection facingTowards;
    private Genes genes;
    private Species species;
    private Gender gender;

    private int daySpawned;
    private int lifetime = 0;

    public Animal(int daySpawned) {
        this.daySpawned = daySpawned;
        this.name = RandomnessHandler.randomName(8);
        this.remainingEnergy = DEFAULT_STARTING_ENERGY;
        this.facingTowards = RandomnessHandler.randomElementFromList(MapDirection.valueList());
        this.genes = new Genes();
        this.species = Species.UNSPECIFIED;
        this.gender = Gender.UNSPECIFIED;
    }

    public Animal reproduce(int currentDay) {
        try {
            if (this.remainingEnergy >= 200) {
                Animal babyAnimal = (Animal) this.clone();
                babyAnimal.getGenes().mutate();
                babyAnimal.name = RandomnessHandler.randomName(8);
                babyAnimal.remainingEnergy /= 2;
                this.remainingEnergy /= 2;
                babyAnimal.facingTowards = RandomnessHandler.randomElementFromList(MapDirection.valueList());
                babyAnimal.daySpawned = currentDay;
                return babyAnimal;
            } else {
                return null;
            }
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException();
        }
    }

    public String getName() {
        return name;
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
        private Map<MoveDirection, Integer> genesMap = new HashMap<>();

        public Genes() {
            for(int i = 0; i <= 7; i++) {
                genesMap.put(MoveDirection.values()[i], RandomnessHandler.randomIntFromRange(0, MAX_GENE_VALUE));
            }
        }

        public void mutate() {
            Map<MoveDirection, Integer> newGenesMap = new HashMap<>();
            MoveDirection pickedDirection = RandomnessHandler.randomElementFromList(MoveDirection.valueList());
            for(Map.Entry<MoveDirection, Integer> entry : genesMap.entrySet()) {
                if(entry.getKey().equals(pickedDirection)) {
                    newGenesMap.put(entry.getKey(), entry.getValue() + RandomnessHandler.randomIntFromRange(-1, 1));
                }
                newGenesMap.put(entry.getKey(), entry.getValue());
            }
            genesMap = newGenesMap;
        }

    }
}
