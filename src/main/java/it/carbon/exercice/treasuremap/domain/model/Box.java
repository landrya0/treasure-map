package it.carbon.exercice.treasuremap.domain.model;

import java.util.List;
import java.util.stream.Collectors;

public record Box(Position position, List<BoxItem> items) {

    public boolean isPlainBox() {
        return items.size() == 1 && items.get(0) instanceof Plain;
    }

    public boolean isMountainBox() {
        return items.size() == 1 && items.get(0) instanceof Mountain;
    }

    public boolean containsTreasure() {
        return items.stream().anyMatch(item -> item instanceof Treasure);
    }

    public boolean hasPlayer() {
        return items.stream().anyMatch(item -> item instanceof Player);
    }

    public long treasuresCount() {
        return items.stream().filter(item -> item instanceof Treasure).count();
    }

    public Player getPlayer() {
        return items.stream().filter(item -> item instanceof Player)
                .map(item -> (Player) item)
                .findFirst().orElse(null);

    }

}
