package it.carbon.exercice.treasuremap.domain.usecase;

import it.carbon.exercice.treasuremap.domain.model.TreasureMap;

import java.util.List;

public interface PlayGameUseCase {

    InitGameResult initGame(InitGameCommand initGameCommand);

    RoundResult playNextRound();

    record InitGameCommand(List<String> commandLine) {
    }

    record RoundResult(TreasureMap treasureMap, boolean hasNextRound) {
    }

    sealed interface InitGameResult {
    }

    final record Success(TreasureMap treasureMap, List<String> warnings) implements InitGameResult {
    }

    final record Failure(List<String> errors, List<String> warnings) implements InitGameResult {
    }
}
