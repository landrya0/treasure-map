package it.carbon.exercice.treasuremap.domain.model;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
                .allMatch(boxItem -> boxItem instanceof Plain);

        assertThat(treasureMap.boxes().stream()
                .map(Box::items))
                .allMatch(box -> box.size() == 1);
    }

    @Test
    public void shouldNotCreateMApToMap_when_DimensionsAreHeightIsNull() {

        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            TreasureMap.builder().build()
        );

        // Then
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldAddMountainsToMap() {
        // Given
        var mapWidth = 3;
        var mapHeight = 4;
        var mountain1Position = new Position(2, 3);
        var mountain2Position = new Position(1, 2);

        // When
        TreasureMap treasureMap = TreasureMap.builder()
                .withWidth(mapWidth)
                .withHeight(mapHeight)
                .withMountain(mountain1Position)
                .withMountain(mountain2Position)
                .build();

        // Then
        assertThat(treasureMap.getBox(new Position(2, 3)).items().get(0)).isInstanceOf(Mountain.class);
        assertThat(treasureMap.getBox(new Position(1, 2)).items().get(0)).isInstanceOf(Mountain.class);
    }

    @Test
    public void shouldIgnoreMountainAdd_when_positionAlreadyContainsOne() {
        // Given
        var mapWidth = 3;
        var mapHeight = 4;
        var position = new Position(2, 3);

        // When
        TreasureMap treasureMap = TreasureMap.builder()
                .withWidth(mapWidth)
                .withHeight(mapHeight)
                .withMountain(position)
                .withMountain(position)
                .build();

        // Then
        assertThat(treasureMap.getBox(new Position(2, 3)).items().get(0)).isInstanceOf(Mountain.class);
    }

    @Test
    public void shouldIgnoreMountainAdd_when_mountainPositionIsInvalid() {

        // Given
        var mapWidth = 3;
        var mapHeight = 4;
        var invalidPosition = new Position(5, 10);

        // When
        TreasureMap treasureMap = TreasureMap.builder()
                .withWidth(mapWidth)
                .withHeight(mapHeight)
                .withMountain(invalidPosition)
                .build();

        //Then
        assertThat(treasureMap.boxes().stream().map(Box::items).flatMap(Collection::stream)
                .noneMatch(item -> item instanceof Mountain))
                .isTrue();
    }

    @Test
    public void shouldAddTreasuresToMap() {
        // Given
        var mapWidth = 3;
        var mapHeight = 4;
        var position = new Position(2, 3);

        // When
        TreasureMap treasureMap = TreasureMap.builder()
                .withWidth(mapWidth)
                .withHeight(mapHeight)
                .withTreasure(position, 3)
                .build();

        //Then
        assertThat(treasureMap.getBox(position).items()).hasSize(3);
        assertThat(treasureMap.getBox(position).items()).allMatch(boxItem -> boxItem instanceof Treasure);
    }

    @Test
    public void shouldNotAddTreasuresToMap_when_MountainWasAlreadyAtPosition() {
        // Given
        var mapWidth = 3;
        var mapHeight = 4;
        var position = new Position(2, 3);

        // When
        TreasureMap treasureMap = TreasureMap.builder()
                .withWidth(mapWidth)
                .withHeight(mapHeight)
                .withMountain(position)
                .withTreasure(position, 3)
                .build();

        //Then
        assertThat(treasureMap.getBox(position).items()).hasSize(1);
        assertThat(treasureMap.getBox(position).items()).allMatch(boxItem -> boxItem instanceof Mountain);
    }

    @Test
    public void shouldNotAddTreasuresToMap_when_TreasuresWasAlreadyAtPosition() {
        // Given
        var mapWidth = 3;
        var mapHeight = 4;
        var position = new Position(2, 3);

        // When
        TreasureMap treasureMap = TreasureMap.builder()
                .withWidth(mapWidth)
                .withHeight(mapHeight)
                .withTreasure(position, 5)
                .withTreasure(position, 3)
                .build();

        //Then
        assertThat(treasureMap.getBox(position).items()).hasSize(5);
        assertThat(treasureMap.getBox(position).items()).allMatch(boxItem -> boxItem instanceof Treasure);
    }

}
