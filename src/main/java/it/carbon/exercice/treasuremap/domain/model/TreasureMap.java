package it.carbon.exercice.treasuremap.domain.model;

import java.util.List;

public record TreasureMap(List<Box> boxes) {

    public Box getBox(Position position) {
        return boxes.stream().filter(box -> box.position().equals(position)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Could not find box at position " + position));
    }

    public int getWidth() {
        return 1 + boxes.stream().map(Box::position).map(Position::horizontalAxis)
                .max(Integer::compareTo).orElseThrow(() -> new IllegalStateException("TreasureMap always have boxes"));
    }

    public int getHeight() {
        return 1 + boxes.stream().map(Box::position).map(Position::verticalAxis)
                .max(Integer::compareTo).orElseThrow(() -> new IllegalStateException("TreasureMap always have boxes"));
    }

    public List<Box> getMountainBoxes() {
        return boxes.stream().filter(Box::isMountainBox).toList();
    }

    public List<Box> getTreasureBoxes() {
        return boxes.stream().filter(Box::containsTreasure).toList();
    }

    public List<Box> getBoxesWithPlayer() {
        return boxes.stream().filter(Box::hasPlayer).toList();
    }

    public static TreasureMapBuilder builder() {
        return new TreasureMapBuilder();
    }
}
