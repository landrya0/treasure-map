package it.carbon.exercice.treasuremap.domain.model;

import java.util.Arrays;

import static java.lang.Math.*;

public enum Orientation {
    SOUTH(2), NORTH(0), EAST(1), WEST(3);

    private final int order;

    Orientation(int order) {
        this.order = order;
    }

    public Orientation getOrientationAfterMotion(Motion motion) {
        return switch (motion) {
            case TURN_RIGHT -> Arrays.stream(values()).filter(orientation -> orientation.order == floorMod(this.order + 1, 4)).findFirst()
                    .orElseThrow(IllegalStateException::new);
            case TURN_LEFT -> Arrays.stream(values()).filter(orientation -> orientation.order == floorMod(this.order - 1, 4)).findFirst()
                    .orElseThrow(IllegalStateException::new);
            case MOVE_FORWARD -> this;
        };
    }
}