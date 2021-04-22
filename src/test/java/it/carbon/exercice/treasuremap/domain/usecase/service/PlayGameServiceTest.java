package it.carbon.exercice.treasuremap.domain.usecase.service;

import it.carbon.exercice.treasuremap.domain.usecase.PlayGameUseCase;

import java.util.List;

class PlayGameServiceTest {

    PlayGameService playGameService = new PlayGameService();

    public void shouldInitGame() {
        // Given
        var initGameCommand = new PlayGameUseCase.InitGameCommand(
                List.of(
                        "C - 3 - 4",
                        "M - 1 - 0",
                        "M - 2 - 1",
                        "T - 0 - 3 - 2",
                        "T - 1 - 3 - 3",
                        "A - Lara - 1 - 1 - S - AADADAGGA"
                )
        );

        // When

        // Then

    }

}