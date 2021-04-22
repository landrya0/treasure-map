package it.carbon.exercice.treasuremap.infrastructure;

import it.carbon.exercice.treasuremap.domain.model.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

@Component
public class MapFileReader {

    private static final String INPUT_FILE_SEPARATOR = "-";

    public static TreasureMap read(String inputFilePath) throws IOException {
        var inputLines = Files.readAllLines(Path.of(inputFilePath));
        return read(inputLines);
    }

    public static TreasureMap readFromClasspath(String inputFilePath) throws IOException {
        InputStream resource = new ClassPathResource(inputFilePath).getInputStream();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource))) {
            var inputLines = reader.lines().collect(Collectors.toList());
            return read(inputLines);
        }
    }

    private static TreasureMap read(List<String> lines) {
        TreasureMapBuilder mapBuilder = TreasureMap.builder();
        lines.stream().map(line -> Arrays.stream(line.trim().split(INPUT_FILE_SEPARATOR)).map(String::trim).toList()).forEach(line -> {
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
                case "A":
                    mapBuilder.withPlayer(new Player(
                            line.get(1),
                            new Position(parseInt(line.get(2)), parseInt(line.get(3))),
                            mapOrientation(line.get(4)),
                            mapMotionSequence(line.get(5))
                    ));
                    break;
                case "#":
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + line.get(0));
            }
        });
        return mapBuilder.build();
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
                .map(MapFileReader::mapMotionSymbol)
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
}
