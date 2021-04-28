package it.carbon.exercice.treasuremap.domain.port;

import it.carbon.exercice.treasuremap.domain.model.Player;

import java.util.List;

public interface PlayerStorageAdapter {
    void storeOrUpdatePlayers(List<Player> players);
    List<Player> getAllPlayers();
}
