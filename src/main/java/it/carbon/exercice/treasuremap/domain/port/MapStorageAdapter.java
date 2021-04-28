package it.carbon.exercice.treasuremap.domain.port;

import it.carbon.exercice.treasuremap.domain.model.TreasureMap;

public interface MapStorageAdapter {

    TreasureMap getMap();
    void storeOrUpdateMap(TreasureMap treasureMap);
}
