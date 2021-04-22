package it.carbon.exercice.treasuremap.infrastructure;

import it.carbon.exercice.treasuremap.domain.model.Orientation;
import it.carbon.exercice.treasuremap.domain.model.TreasureMap;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MapToTextConverter {

    public String convert(TreasureMap treasureMap) {
        return String.join("\n",
                "C - " + treasureMap.getWidth() + " - " + treasureMap.getHeight(),
                treasureMap.getMountainBoxes().stream()
                        .map(box -> "M - " + box.position().horizontalAxis() + " - " + box.position().verticalAxis())
                        .collect(Collectors.joining("\n")),
                treasureMap.getTreasureBoxes().stream()
                        .map(box -> "T - " + box.position().horizontalAxis() + " - " + box.position().verticalAxis() + " - " + box.treasuresCount())
                        .collect(Collectors.joining("\n")),
                treasureMap.getBoxesWithPlayer().stream()
                        .map(box -> "A - " + box.getPlayer().name() + " - " + box.position().horizontalAxis() + " - " + box.position().verticalAxis() + " - "
                        + mapPlayerOrientation(box.getPlayer().getCurrentOrientation()) + " - " + box.getPlayer().treasuresCount())
                        .collect(Collectors.joining("\n"))
        );
    }

    protected String mapPlayerOrientation(Orientation orientation) {
        if(orientation == Orientation.NORTH) {
            return "N";
        }
        if(orientation == Orientation.SOUTH) {
            return "S";
        }
        if(orientation == Orientation.EAST) {
            return "E";
        }
        if(orientation == Orientation.WEST) {
            return "O";
        } else {
            throw new IllegalArgumentException("Orientation not managed");
        }
    }
}
