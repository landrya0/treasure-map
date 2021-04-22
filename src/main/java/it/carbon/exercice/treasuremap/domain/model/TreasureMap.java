package it.carbon.exercice.treasuremap.domain.model;

import java.util.List;

public record TreasureMap(List<Box> boxes) {

    public Box getBox(Position position) {
        return boxes.stream().filter(box -> box.position().equals(position)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Could not find box at position " + position));
    }

    public static TreasureMapBuilder builder() {
        return new TreasureMapBuilder();
    }
}
