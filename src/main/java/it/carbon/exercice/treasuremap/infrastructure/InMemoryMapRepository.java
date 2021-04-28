package it.carbon.exercice.treasuremap.infrastructure;

import it.carbon.exercice.treasuremap.domain.model.TreasureMap;
import it.carbon.exercice.treasuremap.domain.port.MapStorageAdapter;
import org.springframework.stereotype.Component;

@Component
public class InMemoryMapRepository implements MapStorageAdapter {

    private TreasureMap treasureMap;

    @Override
    public TreasureMap getMap() {
        return treasureMap;
    }

    @Override
    public void storeOrUpdateMap(TreasureMap treasureMap) {
        this.treasureMap = treasureMap;
    }
}
