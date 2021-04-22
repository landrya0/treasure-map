package it.carbon.exercice.treasuremap.domain.model;

import java.util.List;

public final record Player(String name, Position initialPosition,
                     Orientation initialOrientation,
                     List<Motion> motionSequence) implements BoxItem {
}
