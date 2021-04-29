package it.carbon.exercice.treasuremap.domain.model;


public final class Box {
    private final Position position;
    private final BoxType type;
    private int treasureQuantity;

    public Box(Position position, BoxType type) {
        this.position = position;
        this.type = type;
        this.treasureQuantity = 0;
    }

    public Box(Position position, int treasureQuantity) {
        this.position = position;
        this.type = BoxType.PLAIN;
        this.treasureQuantity = treasureQuantity;
    }

    public Box(Position position) {
        this.position = position;
        this.type = BoxType.PLAIN;
        this.treasureQuantity = 0;
    }

    public boolean isMountainBox() {
        return type == BoxType.MOUNTAIN;
    }

    public boolean containsTreasure() {
        return treasureQuantity > 0;
    }

    public void retrieveTreasury() {
        if(treasureQuantity > 0) {
            treasureQuantity--;
        }
    }

    public Position getPosition() {
        return position;
    }

    public int getTreasureQuantity() {
        return treasureQuantity;
    }

    enum BoxType {PLAIN, MOUNTAIN}
}
