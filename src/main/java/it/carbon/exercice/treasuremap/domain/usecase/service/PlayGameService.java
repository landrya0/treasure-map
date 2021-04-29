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

    @Override
    public void startGame(TreasureMap treasureMap) {
        mapStorageAdapter.storeOrUpdateMap(treasureMap);
    }

    @Override
    public RoundResult playNextRound() {
        TreasureMap treasureMap = mapStorageAdapter.getMap();

        treasureMap.getPlayers().forEach(Player::playNextRound);

        // Etant donné qu'on est en mémoire les deux prochaine lignes ne sont pas utiles.
        // MAis c'est juste pour simuler le cas ou on veut sauvegarder l'etat du jeu en base à chaque round
        mapStorageAdapter.storeOrUpdateMap(treasureMap);

        return new RoundResult(treasureMap, treasureMap.getPlayers().stream().anyMatch(player -> player.getRemainingMotionsCount() > 0));
    }
}
