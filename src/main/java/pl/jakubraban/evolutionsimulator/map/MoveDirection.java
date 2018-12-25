package pl.jakubraban.evolutionsimulator.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum MoveDirection {

    FORWARD, FORWARD_RIGHT, RIGHT, BACKWARD_RIGHT, BACKWARD, BACKWARD_LEFT, LEFT, FORWARD_LEFT;

    public String toString() {
        return this.name();
    }

    public static List<MoveDirection> valueList() {
        return new ArrayList<>(Arrays.asList(MoveDirection.values()));
    }

}
