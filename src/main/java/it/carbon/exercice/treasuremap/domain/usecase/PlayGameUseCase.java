package it.carbon.exercice.treasuremap.domain.usecase;

import it.carbon.exercice.treasuremap.domain.model.Player;
import it.carbon.exercice.treasuremap.domain.model.TreasureMap;

import java.util.List;

public interface PlayGameUseCase {

    void startGame(TreasureMap treasureMap, List<Player> players);

    RoundResult playNextRound();

    record InitGameCommand(List<String> commandLine) {
    }

    record RoundResult(TreasureMap treasureMap, List<Player> players, boolean hasNextRound) {
    }
}
