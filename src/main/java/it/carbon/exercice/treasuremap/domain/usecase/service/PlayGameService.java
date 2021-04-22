package it.carbon.exercice.treasuremap.domain.usecase.service;

import it.carbon.exercice.treasuremap.domain.model.TreasureMap;
import it.carbon.exercice.treasuremap.domain.usecase.PlayGameUseCase;
import org.springframework.stereotype.Component;

@Component
public class PlayGameService implements PlayGameUseCase {

    private TreasureMap treasureMap; //TODO: extraire dans une source de données

    @Override
    public void startGame(TreasureMap treasureMap) {
        this.treasureMap = treasureMap;
    }

    @Override
    public RoundResult playNextRound() {
        // TODO: Perform Game Algorithm here
        return new RoundResult(treasureMap, false);
    }
}
