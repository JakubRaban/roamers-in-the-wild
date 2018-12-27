package pl.jakubraban.evolutionsimulator.entities;

import pl.jakubraban.evolutionsimulator.map.MapDirection;
import pl.jakubraban.evolutionsimulator.map.MoveDirection;
import pl.jakubraban.evolutionsimulator.map.Position;
import pl.jakubraban.evolutionsimulator.randomness.RandomnessHandler;

import java.util.HashMap;
import java.util.Map;

public class Animal implements Cloneable {

    public static final int DEFAULT_STARTING_ENERGY = 1000;

    private String name;

    private Position position;
    private int remainingEnergy;
    private MapDirection facingTowards;
    private Genes genes;
    private Species species;
    private Gender gender;

    private int daySpawned;
    private int lifetime = 0;

    public Animal(Position position, int daySpawned) {
        setPosition(position);
        this.daySpawned = daySpawned;
        this.name = RandomnessHandler.randomName(8);
        this.remainingEnergy = DEFAULT_STARTING_ENERGY;
        this.facingTowards = RandomnessHandler.randomElementFromList(MapDirection.valueList());
        this.genes = new Genes();
        this.species = Species.UNSPECIFIED;
        this.gender = Gender.UNSPECIFIED;
    }

    public void move() {
        MapDirection currentDirection = getFacingDirection();
        MoveDirection turningTowards = getTurnDirection();
        MapDirection newDirection = currentDirection.directionAfterTurning(turningTowards);
        setPosition(getPosition().add(new Position(newDirection.getXStep(), newDirection.getYStep())));
        setFacingDirection(newDirection);
        this.remainingEnergy--;
    }

    private MoveDirection getTurnDirection() {
        return RandomnessHandler.randomElementByRelativeProbability(getGenes().getGenesMap());
    }

    public Animal reproduce(int currentDay) {
        try {
            if (this.ableToReproduce()) {
                Animal babyAnimal = (Animal) super.clone();
                babyAnimal.genes = this.getGenes().mutate();
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

    private Genes getGenes() {
        return genes;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setFacingDirection(MapDirection facingTowards) {
        this.facingTowards = facingTowards;
    }

    public void addEnergy(int newEnergy) {
        this.remainingEnergy += newEnergy;
    }

    private boolean ableToReproduce() {
        return getRemainingEnergy() >= 200;
    }





    private enum Species {
        UNSPECIFIED
    }




    private enum Gender {
        MALE, FEMALE, UNSPECIFIED
    }




    private class Genes {

        public static final int MAX_STARTING_GENE_VALUE = 10;
        private Map<MoveDirection, Integer> genesMap = new HashMap<>();

        public Genes() {
            for(int i = 0; i <= 7; i++) {
                genesMap.put(MoveDirection.values()[i], RandomnessHandler.randomIntFromRange(0, MAX_STARTING_GENE_VALUE));
            }
        }

        private Genes(Map<MoveDirection, Integer> genesMap) {
            this.genesMap = genesMap;
        }

        public Genes mutate() {
            MoveDirection pickedDirection = RandomnessHandler.randomElementFromList(MoveDirection.valueList());
            int geneDifference = RandomnessHandler.randomIntFromRange(-1, 1);
            int toBeChanged = genesMap.get(pickedDirection);
            int newValue = toBeChanged + geneDifference;
            if(newValue < 0) newValue = 0;
            Map<MoveDirection, Integer> newGenesMap = new HashMap<>();
            for(Map.Entry<MoveDirection, Integer> gene : genesMap.entrySet()) {
                if(gene.getKey().equals(pickedDirection)) newGenesMap.put(gene.getKey(), newValue);
                else newGenesMap.put(gene.getKey(), gene.getValue());
            }
            return new Genes(newGenesMap);
        }

        public Map<MoveDirection, Integer> getGenesMap() {
            return genesMap;
        }

    }
}
