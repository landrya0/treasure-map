package it.carbon.exercice.treasuremap.domain.model;

public interface Orientation {
    Orientation SOUTH = new South();
    Orientation NORTH = new North();
    Orientation EAST = new East();
    Orientation WEST = new West();
}

final class North implements Orientation {
}

final class South implements Orientation {
}

final class East implements Orientation {
}

final class West implements Orientation {
}