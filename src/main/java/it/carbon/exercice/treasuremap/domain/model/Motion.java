package it.carbon.exercice.treasuremap.domain.model;

public sealed interface Motion {
    Motion MOVE_FORWARD = new MoveForward();
    Motion TURN_LEFT = new TurnLeft();
    Motion TURN_RIGHT = new TurnRight();
}

final class MoveForward implements Motion {
}

final class TurnLeft implements Motion {
}

final class TurnRight implements Motion {
}
