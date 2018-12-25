package pl.jakubraban.evolutionsimulator.randomness;

public class Probability {

    private int probabilityInPercent;

    public Probability(int probabilityInPercent) {
        if(probabilityInPercent < 0 || probabilityInPercent > 100) throw new IllegalArgumentException("Bad probability percentage");
        this.probabilityInPercent = probabilityInPercent;
    }

    public int getProbabilityInPercent() {
        return probabilityInPercent;
    }
}
