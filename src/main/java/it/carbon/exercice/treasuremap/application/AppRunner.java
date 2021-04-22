package it.carbon.exercice.treasuremap.application;

import it.carbon.exercice.treasuremap.domain.model.TreasureMap;
import it.carbon.exercice.treasuremap.domain.usecase.PlayGameUseCase;
import it.carbon.exercice.treasuremap.infrastructure.MapFileReader;
import it.carbon.exercice.treasuremap.infrastructure.MapToTextConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    public static final String DEFAULT_INPUT_FILE = "default-input-file.txt";

    @Autowired
    private PlayGameUseCase playGameUseCase;
    @Autowired
    private MapToTextConverter mapToTextConverter;

    @Override
    public void run(String... args) throws Exception {

        TreasureMap treasureMap = args.length > 0 ?
                MapFileReader.read(args[0]) :
                MapFileReader.readFromClasspath(DEFAULT_INPUT_FILE);


        playGameUseCase.startGame(treasureMap);
        PlayGameUseCase.RoundResult roundResult;
        do {
            roundResult = playGameUseCase.playNextRound();
        } while (roundResult.hasNextRound());

        System.out.println(mapToTextConverter.convert(treasureMap));


    }
}
