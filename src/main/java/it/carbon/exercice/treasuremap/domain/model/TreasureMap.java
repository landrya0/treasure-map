package it.carbon.exercice.treasuremap.domain.model;

import java.util.ArrayList;
import java.util.List;

public final class TreasureMap {
    private final List<Box> boxes;
    private List<Player> players = new ArrayList<>();

    public TreasureMap(List<Box> boxes) {
        this.boxes = boxes;
    }

    public int getWidth() {
        return 1 + boxes.stream().map(Box::getPosition).map(Position::horizontalAxis)
                .max(Integer::compareTo).orElseThrow(() -> new IllegalStateException("TreasureMap always have boxes"));
    }

    public int getHeight() {
        return 1 + boxes.stream().map(Box::getPosition).map(Position::verticalAxis)
                .max(Integer::compareTo).orElseThrow(() -> new IllegalStateException("TreasureMap always have boxes"));
    }

    public List<Box> getMountainBoxes() {
        return boxes.stream().filter(Box::isMountainBox).toList();
    }

    public List<Box> getTreasureBoxes() {
        return boxes.stream().filter(Box::containsTreasure).toList();
    }

    public void pickUpTreasure(Position position) {
        boxes.stream()
                .filter(box -> box.getPosition().equals(position))
                .forEach(box -> {
                    if (box.containsTreasure()) {
                        box.retrieveTreasury();
                    }
                });
    }

    public static TreasureMapBuilder builder() {
        return new TreasureMapBuilder();
    }

    public Box getNeighborAvailableBox(Position position, Orientation orientation) {

        Position newPosition = switch (orientation) {
            case EAST -> new Position(position.horizontalAxis() + 1, position.verticalAxis());
            case WEST -> new Position(position.horizontalAxis() - 1, position.verticalAxis());
            case NORTH -> new Position(position.horizontalAxis(), position.verticalAxis() - 1);
            case SOUTH -> new Position(position.horizontalAxis(), position.verticalAxis() + 1);
        };

        return boxes.stream()
                .filter(box -> box.getPosition().equals(newPosition))
                .filter(box -> !box.isMountainBox())
                .filter(box -> players.stream().noneMatch(player -> player.getPosition().equals(box.getPosition())))
                .findFirst().orElse(null);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
