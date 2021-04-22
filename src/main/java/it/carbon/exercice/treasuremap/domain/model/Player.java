package it.carbon.exercice.treasuremap.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Player implements BoxItem {
    private final String name;
    private final Position initialPosition;
    private final Orientation initialOrientation;
    private final List<Motion> motionSequence;
    private final List<Treasure> collectedTreasures = new ArrayList<>();
    private Orientation currentOrientation;

    public Player(String name, Position initialPosition,
                  Orientation initialOrientation,
                  List<Motion> motionSequence) {
        this.name = name;
        this.initialPosition = initialPosition;
        this.initialOrientation = initialOrientation;
        this.motionSequence = motionSequence;
        this.currentOrientation = initialOrientation;
    }

    public String name() {
        return name;
    }

    public Position initialPosition() {
        return initialPosition;
    }

    public Orientation initialOrientation() {
        return initialOrientation;
    }

    public List<Motion> motionSequence() {
        return motionSequence;
    }

    public int treasuresCount() {
        return collectedTreasures.size();
    }

    public Orientation getCurrentOrientation() {
        return currentOrientation;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Player) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.initialPosition, that.initialPosition) &&
                Objects.equals(this.initialOrientation, that.initialOrientation) &&
                Objects.equals(this.motionSequence, that.motionSequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, initialPosition, initialOrientation, motionSequence);
    }

    @Override
    public String toString() {
        return "Player[" +
                "name=" + name + ", " +
                "initialPosition=" + initialPosition + ", " +
                "initialOrientation=" + initialOrientation + ", " +
                "motionSequence=" + motionSequence + ", " +
                "collectedTreasures=" + collectedTreasures + ']';
    }

}
