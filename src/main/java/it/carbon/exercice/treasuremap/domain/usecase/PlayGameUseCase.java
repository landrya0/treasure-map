package it.carbon.exercice.treasuremap.domain.usecase;

import it.carbon.exercice.treasuremap.domain.model.TreasureMap;

public interface PlayGameUseCase {

    void startGame(TreasureMap treasureMap);

    RoundResult playNextRound();

    record RoundResult(TreasureMap treasureMap, boolean hasNextRound) {
    }
}
