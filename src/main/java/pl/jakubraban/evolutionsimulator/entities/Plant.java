package pl.jakubraban.evolutionsimulator.entities;

import pl.jakubraban.evolutionsimulator.map.Position;

public class Plant {

    public static final int DEFAULT_ENERGY_STORED = 80;

    private final Position position;
    private final int energyStored;

    public Plant(Position position) {
        this.position = position;
        this.energyStored = DEFAULT_ENERGY_STORED;
    }

    public Position getPosition() {
        return position;
    }

    public int getEnergyStored() {
        return energyStored;
    }
}
