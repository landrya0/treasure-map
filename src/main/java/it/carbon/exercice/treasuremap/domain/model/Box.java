package it.carbon.exercice.treasuremap.domain.model;

import java.util.List;

public record Box(Position position, List<BoxItem> items) {

    public boolean isPlainBox() {
        return items.size() == 1 && items.get(0) instanceof Plain;
    }
}
