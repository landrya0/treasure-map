package it.carbon.exercice.treasuremap.domain.model;

import java.util.List;

public final class Player {
    private final String name;
    private final List<Motion> motionSequence;
    private final TreasureMap treasureMap;
    protected Position position;
    protected Orientation orientation;
    protected int treasureQuantity = 0;
    private int remainingMotionsCount;

    public Player(String name, List<Motion> motionSequence, TreasureMap treasureMap, Position position, Orientation orientation) {
        this.name = name;
        this.motionSequence = motionSequence;
        this.treasureMap = treasureMap;
        this.position = position;
        this.orientation = orientation;
        this.remainingMotionsCount = motionSequence.size();
    }

    public TreasureMap getTreasureMap() {
        return treasureMap;
    }

    public Position getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public int getTreasureQuantity() {
        return treasureQuantity;
    }

    public int getRemainingMotionsCount() {
        return remainingMotionsCount;
    }

    public List<Motion> getMotionSequence() {
        return motionSequence;
    }

    public String name() {
        return name;
    }

    public void playNextRound() {
        if (remainingMotionsCount > 0) {
            Motion nextMotion = motionSequence.get(motionSequence.size() - remainingMotionsCount);
            switch (nextMotion) {
                case MOVE_FORWARD -> moveForward();
                case TURN_LEFT, TURN_RIGHT -> this.orientation = this.orientation.getOrientationAfterMotion(nextMotion);
                default -> throw new UnsupportedOperationException("Motion " + nextMotion);
            }
            remainingMotionsCount--;
        }
    }

    public void moveForward() {
        Box neighborAvailableBox = treasureMap.getNeighborAvailableBox(position, orientation);
        if (neighborAvailableBox != null) {
            Position newPosition = neighborAvailableBox.getPosition();
            this.position = newPosition;
            if (neighborAvailableBox.containsTreasure()) {
                treasureMap.pickUpTreasure(newPosition);
                treasureQuantity++;
            }
        }
    }

}
