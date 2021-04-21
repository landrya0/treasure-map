package it.carbon.exercice.treasuremap.domain.model;

import java.util.ArrayList;
import java.util.List;

public record TreasureMap(List<Box> boxes) {

    public static TreasureMapBuilder builder() {
        return new TreasureMapBuilder();
    }

    static class TreasureMapBuilder {
        private int width;
        private int height;

        public TreasureMapBuilder withWidth(int width) {
            this.width = width;
            return this;
        }

        public TreasureMapBuilder withHeight(int height) {
            this.height = height;
            return this;
        }

        public TreasureMap build() {
            List<Box> boxList = new ArrayList<>();
            for (int horizontalAxis = 0; horizontalAxis < width; horizontalAxis++) {
                for (int verticalAxis = 0; verticalAxis < height; verticalAxis++) {
                    boxList.add(new Box(new Position(horizontalAxis, verticalAxis), List.of(new BoxItem())));
                }
            }
            return new TreasureMap(boxList);
        }
    }
}
