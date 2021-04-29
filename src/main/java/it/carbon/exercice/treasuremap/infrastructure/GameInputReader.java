package it.carbon.exercice.treasuremap.infrastructure;

import it.carbon.exercice.treasuremap.domain.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

@Component
public class GameInputReader {

    @Value("${input.default.file.path}")
    private String inputDefaultFilePath;
    @Value("${input.file.separator}")
    private String inputFileSeparator;

    public TreasureMap read(String inputFilePath) throws IOException {
        var inputLines = Files.readAllLines(Path.of(inputFilePath));

        return read(inputLines);
    }

    public TreasureMap readFromClasspath() throws IOException {
        InputStream resource = new ClassPathResource(inputDefaultFilePath).getInputStream();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource))) {
            var inputLines = reader.lines().collect(Collectors.toList());
            return read(inputLines);
        }
    }

    private TreasureMap read(List<String> lines) {
        List<Player> players = new ArrayList<>();
        TreasureMapBuilder mapBuilder = TreasureMap.builder();
        List<List<String>> linesArrayLists = lines.stream()
                .map(line -> Arrays.stream(line.trim().split(inputFileSeparator))
                .map(String::trim).toList())
                .toList();
        linesArrayLists.forEach(line -> {
            switch (line.get(0)) {
                case "C":
                    mapBuilder.withWidth(parseInt(line.get(1)));
                    mapBuilder.withHeight(parseInt(line.get(2)));
                    break;
                case "M":
                    mapBuilder.withMountain(new Position(parseInt(line.get(1)), parseInt(line.get(2))));
                    break;
                case "T":
                    mapBuilder.withTreasure(new Position(parseInt(line.get(1)), parseInt(line.get(2))), parseInt(line.get(3)));
                    break;
                case "A", "#":
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + line.get(0));
            }
        });

        TreasureMap treasureMap = mapBuilder.build();

        linesArrayLists.forEach(line -> {
            if (line.get(0).equals("A")) {
                Player player = new Player(
                        line.get(1),
                        mapMotionSequence(line.get(5)),
                        treasureMap,
                        new Position(parseInt(line.get(2)), parseInt(line.get(3))),
                        mapOrientation(line.get(4))
                );
                players.add(player);
            }
        });

        treasureMap.setPlayers(players);

        return treasureMap;
    }

    private static Orientation mapOrientation(String symbol) {
        return switch (symbol) {
            case "S":
                yield Orientation.SOUTH;
            case "N":
                yield Orientation.NORTH;
            case "E":
                yield Orientation.EAST;
            case "O":
                yield Orientation.WEST;
            default:
                throw new IllegalStateException("Unexpected value: " + symbol);
        };
    }

    private static List<Motion> mapMotionSequence(String motionSequenceString) {
        return Arrays.stream(motionSequenceString.split(""))
                .map(GameInputReader::mapMotionSymbol)
                .collect(Collectors.toList());
    }

    private static Motion mapMotionSymbol(String symbol) {
        return switch (symbol) {
            case "A":
                yield Motion.MOVE_FORWARD;
            case "D":
                yield Motion.TURN_RIGHT;
            case "G":
                yield Motion.TURN_LEFT;
            default:
                throw new IllegalStateException("Unexpected value: " + symbol);
        };
    }

    public record GameData(TreasureMap treasureMap, List<Player> players) {
    }
}
