package it.carbon.exercice.treasuremap.domain.model;

import java.util.List;

public record Box(Position position, List<BoxItem> items) {
}
