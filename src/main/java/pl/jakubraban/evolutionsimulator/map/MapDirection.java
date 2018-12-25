package pl.jakubraban.evolutionsimulator.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum MapDirection {

    NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST;

    public String toString() {
        return this.name();
    }

    public static List<MapDirection> valueList() {
        return new ArrayList<>(Arrays.asList(MapDirection.values()));
    }

}
