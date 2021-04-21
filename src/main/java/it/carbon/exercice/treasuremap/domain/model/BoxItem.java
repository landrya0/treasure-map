package it.carbon.exercice.treasuremap.domain.model;

public abstract sealed class BoxItem {
}

final class Plain extends BoxItem {
}

final class Mountain extends BoxItem {
}

final class Treasure extends  BoxItem {
}



