package it.carbon.exercice.treasuremap.application;

import it.carbon.exercice.treasuremap.domain.model.TreasureMap;
import it.carbon.exercice.treasuremap.domain.usecase.PlayGameUseCase;
import it.carbon.exercice.treasuremap.infrastructure.GameInputReader;
import it.carbon.exercice.treasuremap.infrastructure.GameOutputWritter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private PlayGameUseCase playGameUseCase;
    @Autowired
    private GameOutputWritter gameOutputWritter;
    @Autowired
    private GameInputReader gameInputReader;

    @Override
    public void run(String... args) throws Exception {

        TreasureMap treasureMap = args.length > 0 ?
                gameInputReader.read(args[0]) :
                gameInputReader.readFromClasspath();

        playGameUseCase.startGame(treasureMap);
        PlayGameUseCase.RoundResult roundResult;
        int round = 0;
        do {
            roundResult = playGameUseCase.playNextRound();
            System.out.println("--------------------- Round " + round + " -------------------");
            System.out.println(gameOutputWritter.convertToString(roundResult.treasureMap()));
            round++;
        } while (roundResult.hasNextRound());

        gameOutputWritter.writeToFile(roundResult.treasureMap());
    }
}
