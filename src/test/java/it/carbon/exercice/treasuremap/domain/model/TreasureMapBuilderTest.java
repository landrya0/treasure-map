package it.carbon.exercice.treasuremap.domain.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TreasureMapBuilderTest {

    @Test
    public void should_build_valid_boxes_with_default_items() {

        // Given
        var mapWidth = 3;
        var mapHeight = 4;

        // When
        TreasureMap treasureMap = TreasureMap.builder().withWidth(mapWidth).withHeight(mapHeight).build();

        // Then
        assertThat(treasureMap.boxes()).hasSize(12);

        assertThat(treasureMap.boxes().stream().map(Box::position))
                .containsAll(List.of(
                        new Position(0, 0),
                        new Position(0, 1),
                        new Position(0, 2),
                        new Position(0, 3),
                        new Position(1, 0),
                        new Position(1, 1),
                        new Position(1, 2),
                        new Position(1, 3),
                        new Position(2, 0),
                        new Position(2, 1),
                        new Position(2, 2),
                        new Position(2, 3)
                ));

        assertThat(treasureMap.boxes().stream()
                .flatMap(box -> box.items().stream()))
                .allMatch(boxItem -> boxItem.boxItemType() == BoxItem.BoxItemType.PLAIN);

        assertThat(treasureMap.boxes().stream()
                .map(Box::items))
                .allMatch(box -> box.size() == 1);
    }


}
