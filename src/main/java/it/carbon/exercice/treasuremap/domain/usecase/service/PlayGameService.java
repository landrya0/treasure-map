package it.carbon.exercice.treasuremap.domain.usecase.service;

import it.carbon.exercice.treasuremap.domain.model.Player;
import it.carbon.exercice.treasuremap.domain.model.TreasureMap;
import it.carbon.exercice.treasuremap.domain.port.MapStorageAdapter;
import it.carbon.exercice.treasuremap.domain.port.PlayerStorageAdapter;
import it.carbon.exercice.treasuremap.domain.usecase.PlayGameUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayGameService implements PlayGameUseCase {

    @Autowired
    private MapStorageAdapter mapStorageAdapter;
    @Autowired
    private PlayerStorageAdapter playerStorageAdapter;

    @Override
    public void startGame(TreasureMap treasureMap, List<Player> players) {
        mapStorageAdapter.storeOrUpdateMap(treasureMap);
        playerStorageAdapter.storeOrUpdatePlayers(players);
    }

    @Override
    public RoundResult playNextRound() {
        TreasureMap treasureMap = mapStorageAdapter.getMap();
        List<Player> players = playerStorageAdapter.getAllPlayers();

        players.forEach(Player::playNextRound);

        // Etant donné qu'on est en mémoire les deux prochaine lignes ne sont pas utiles.
        // MAis c'est juste pour simuler le cas ou on veut sauvegarder l'etat du jeu en base à chaque round
        mapStorageAdapter.storeOrUpdateMap(treasureMap);
        playerStorageAdapter.storeOrUpdatePlayers(players);
        
        return new RoundResult(treasureMap, players, players.stream().anyMatch(player -> player.getRemainingMotionsCount() > 0));
    }
}
