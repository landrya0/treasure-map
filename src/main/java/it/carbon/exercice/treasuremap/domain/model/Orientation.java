package it.carbon.exercice.treasuremap.domain.model;

public sealed interface Orientation permits North, South, East, West{
    Orientation SOUTH = new South();
    Orientation NORTH = new North();
    Orientation EAST = new East();
    Orientation WEST = new West();
}

final record North() implements Orientation {
}

final record South() implements Orientation {
}

final record East() implements Orientation {
}

final record West() implements Orientation {
}