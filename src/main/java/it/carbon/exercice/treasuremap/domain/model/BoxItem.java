package it.carbon.exercice.treasuremap.domain.model;

public sealed interface BoxItem permits Plain, Mountain, Treasure, Player {
}

final class Plain implements BoxItem {
}

final class Mountain implements BoxItem {
}

final class Treasure implements BoxItem {
}



