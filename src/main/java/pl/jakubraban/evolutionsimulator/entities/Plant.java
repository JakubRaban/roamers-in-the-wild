package pl.jakubraban.evolutionsimulator.entities;

public class Plant {

    public static final int DEFAULT_ENERGY_STORED = 80;

    private final int energyStored;

    public Plant() {
        this.energyStored = DEFAULT_ENERGY_STORED;
    }

    public Plant(int energyStored) {
        this.energyStored = energyStored;
    }

    public int getEnergyStored() {
        return energyStored;
    }
}
