package pl.jakubraban.evolutionsimulator.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum MapDirection {

    NORTH(0,1), NORTH_EAST(1,1), EAST(1,0), SOUTH_EAST(1,-1),
    SOUTH(0,-1), SOUTH_WEST(-1,-1), WEST(-1,0), NORTH_WEST(-1,1);

    private int xStep, yStep;

    MapDirection(int xStep, int yStep) {
        this.xStep = xStep;
        this.yStep = yStep;
    }

    public int getXStep() {
        return xStep;
    }

    public int getYStep() {
        return yStep;
    }

    public MapDirection directionAfterTurning(MoveDirection moveDirection) {
        int thisDirectionNumber = this.ordinal();
        int moveDirectionNumber = moveDirection.ordinal();
        int newMapDirectionOrdinal = (thisDirectionNumber + moveDirectionNumber) % 8;
        return MapDirection.values()[newMapDirectionOrdinal];
    }

    public String toString() {
        return this.name();
    }

    public static List<MapDirection> valueList() {
        return new ArrayList<>(Arrays.asList(MapDirection.values()));
    }

}
