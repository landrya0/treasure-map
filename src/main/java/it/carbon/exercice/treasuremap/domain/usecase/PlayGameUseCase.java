package it.carbon.exercice.treasuremap.domain.usecase;

import java.util.List;

public interface PlayGameUseCase {

    InitGameResult initGame(InitGameCommand initGameCommand);

    RoundResult playNextRound();

    record InitGameCommand(List<String> commandLine) {
    }
}
