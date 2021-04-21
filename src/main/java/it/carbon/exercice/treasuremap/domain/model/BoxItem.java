package it.carbon.exercice.treasuremap.domain.model;

public record BoxItem(BoxItemType boxItemType) {

    public BoxItem() {
        this(BoxItemType.PLAIN);
    }

    enum BoxItemType {
        PLAIN, MOUNTAIN, TREASURE
    }
}


