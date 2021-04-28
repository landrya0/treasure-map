package it.carbon.exercice.treasuremap.domain.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreasureMapBuilder {
    private Integer width;
    private Integer height;
    private final List<Position> mountainPositions = new ArrayList<>();
    private final Map<Position, Integer> treasureRecords = new HashMap<>();

    public TreasureMapBuilder withWidth(int width) {
        this.width = width;
        return this;
    }

    public TreasureMapBuilder withHeight(int height) {
        this.height = height;
        return this;
    }

    public TreasureMapBuilder withMountain(Position position) {
        if (!mountainPositions.contains(position) && !treasureRecords.containsKey(position)) {
            mountainPositions.add(position);
        }
        return this;
    }

    public TreasureMapBuilder withTreasure(Position position, int quantity) {
        if (!mountainPositions.contains(position) && !treasureRecords.containsKey(position)) {
            if (quantity > 0) {
                treasureRecords.put(position, quantity);
            } else {
                throw new IllegalArgumentException("error at position " + position + ": a quantity cannot be negative");
            }
        }
        return this;
    }

    public TreasureMap build() {
        List<Box> boxList = new ArrayList<>();
        if (width == null || height == null) {
            throw new IllegalArgumentException("Width and height are required to build treasureMap");
        }
        for (int horizontalAxis = 0; horizontalAxis < width; horizontalAxis++) {
            for (int verticalAxis = 0; verticalAxis < height; verticalAxis++) {
                Position currentPosition = new Position(horizontalAxis, verticalAxis);
                if (mountainPositions.contains(currentPosition)) {
                    boxList.add(new Box(currentPosition, Box.BoxType.MOUNTAIN));
                } else if (treasureRecords.containsKey(currentPosition)) {
                    int quantity = treasureRecords.get(currentPosition);
                    boxList.add(new Box(currentPosition, quantity));
                } else {
                    Box box = new Box(currentPosition);
                    boxList.add(box);
                }
            }
        }
        return new TreasureMap(boxList);
    }
}
