package it.carbon.exercice.treasuremap.domain.model;

import java.util.ArrayList;
import java.util.List;

public record TreasureMap(List<Box> boxes) {

    public Box getBox(Position position) {
        return boxes.stream().filter(box -> box.position().equals(position)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Could not find box at position " + position));
    }

    public static TreasureMapBuilder builder() {
        return new TreasureMapBuilder();
    }

    static class TreasureMapBuilder {
        private int width;
        private int height;
        private final List<Position> mountainPositions = new ArrayList<>();

        public TreasureMapBuilder withWidth(int width) {
            this.width = width;
            return this;
        }

        public TreasureMapBuilder withHeight(int height) {
            this.height = height;
            return this;
        }

        public TreasureMapBuilder withMountain(Position position) {
            if(!mountainPositions.contains(position)) {
                mountainPositions.add(position);
            }
            return this;
        }

        public TreasureMap build() {
            List<Box> boxList = new ArrayList<>();
            for (int horizontalAxis = 0; horizontalAxis < width; horizontalAxis++) {
                for (int verticalAxis = 0; verticalAxis < height; verticalAxis++) {
                    Position currentPosition = new Position(horizontalAxis, verticalAxis);
                    if(mountainPositions.contains(currentPosition)) {
                        boxList.add(new Box(currentPosition, List.of(new Mountain())));
                    }
                    boxList.add(new Box(currentPosition, List.of(new Plain())));
                }
            }
            return new TreasureMap(boxList);
        }
    }
}
