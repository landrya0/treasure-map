package it.carbon.exercice.treasuremap.domain.usecase;

import it.carbon.exercice.treasuremap.domain.model.TreasureMap;

public record RoundResult(TreasureMap treasureMap, boolean hasNextRound){}
