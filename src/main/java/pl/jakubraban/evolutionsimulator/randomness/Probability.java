package pl.jakubraban.evolutionsimulator.randomness;

import java.util.Random;

public class Probability {

    private int probabilityInPercent;

    public Probability(int probabilityInPercent) {
        if(probabilityInPercent < 0 || probabilityInPercent > 100) throw new IllegalArgumentException("Bad probability percentage");
        this.probabilityInPercent = probabilityInPercent;
    }

    public int getProbabilityInPercent() {
        return probabilityInPercent;
    }

    public boolean roulette() {
        return new Random().nextInt(100) + 1 <= getProbabilityInPercent();
    }
}
