package it.carbon.exercice.treasuremap.infrastructure;

import it.carbon.exercice.treasuremap.domain.model.Orientation;
import it.carbon.exercice.treasuremap.domain.model.Player;
import it.carbon.exercice.treasuremap.domain.model.TreasureMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameOutputWritter {

    @Value("${output.default.file.path}")
    private String outputDefaultFilePath;

    public void writeToFile(TreasureMap treasureMap, List<Player> players) throws IOException {
        String resultAsString = convertToString(treasureMap, players);
        Path path = Paths.get(outputDefaultFilePath);
        byte[] strToBytes = resultAsString.getBytes();
        Files.write(path, strToBytes);
    }

    public String convertToString(TreasureMap treasureMap, List<Player> players) {
        return String.join("\n",
                "C - " + treasureMap.getWidth() + " - " + treasureMap.getHeight(),
                treasureMap.getMountainBoxes().stream()
                        .map(box -> "M - " + box.getPosition().horizontalAxis() + " - " + box.getPosition().verticalAxis())
                        .collect(Collectors.joining("\n")),
                treasureMap.getTreasureBoxes().stream()
                        .map(box -> "T - " + box.getPosition().horizontalAxis() + " - " + box.getPosition().verticalAxis() + " - " + box.getTreasureQuantity())
                        .collect(Collectors.joining("\n")),
                players.stream()
                        .map(player -> "A - " + player.name() + " - " + player.getPosition().horizontalAxis() + " - " + player.getPosition().verticalAxis() + " - "
                                + mapPlayerOrientation(player.getOrientation()) + " - " + player.getTreasureQuantity())
                        .collect(Collectors.joining("\n"))
        );
    }

    protected String mapPlayerOrientation(Orientation orientation) {
        if (orientation == Orientation.NORTH) {
            return "N";
        }
        if (orientation == Orientation.SOUTH) {
            return "S";
        }
        if (orientation == Orientation.EAST) {
            return "E";
        }
        if (orientation == Orientation.WEST) {
            return "O";
        } else {
            throw new IllegalArgumentException("Orientation not managed");
        }
    }
}
