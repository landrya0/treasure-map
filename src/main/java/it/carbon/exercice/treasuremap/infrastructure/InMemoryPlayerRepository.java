package it.carbon.exercice.treasuremap.infrastructure;

import it.carbon.exercice.treasuremap.domain.model.Player;
import it.carbon.exercice.treasuremap.domain.port.PlayerStorageAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InMemoryPlayerRepository implements PlayerStorageAdapter {

    private List<Player> players;

    @Override
    public void storeOrUpdatePlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public List<Player> getAllPlayers() {
        return players;
    }
}
