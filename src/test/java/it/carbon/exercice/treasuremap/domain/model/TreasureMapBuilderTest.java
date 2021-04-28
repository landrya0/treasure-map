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

        assertThat(treasureMap.boxes().stream().map(Box::getPosition))
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

        assertThat(treasureMap.boxes().stream()).allMatch(box -> box.getType() == Box.BoxType.PLAIN);
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
        assertThat(treasureMap.getBox(new Position(2, 3)).getType()).isEqualTo(Box.BoxType.MOUNTAIN);
        assertThat(treasureMap.getBox(new Position(1, 2)).getType()).isEqualTo(Box.BoxType.MOUNTAIN);
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
        assertThat(treasureMap.getBox(new Position(2, 3)).getType()).isEqualTo(Box.BoxType.MOUNTAIN);
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
        assertThat(treasureMap.boxes().stream()).noneMatch(box -> box.getType().equals(Box.BoxType.MOUNTAIN));
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
        assertThat(treasureMap.getBox(position).containsTreasure()).isTrue();
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
        assertThat(treasureMap.getBox(position).isMountainBox()).isTrue();
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
        assertThat(treasureMap.getBox(position).containsTreasure()).isTrue();
    }

   /* @Test
    public void shouldAddPlayers() {
        // Given
        var mapWidth = 3;
        var mapHeight = 4;
        var mountainPosition = new Position(2, 3);
        var treasurePosition = new Position(2, 2);
        var player1 = new Player("Jean", new Position(1, 1), Orientation.SOUTH,
                List.of(MOVE_FORWARD, TURN_LEFT, TURN_RIGHT));

        // When
        TreasureMap treasureMap = TreasureMap.builder()
                .withWidth(mapWidth)
                .withHeight(mapHeight)
                .withMountain(mountainPosition)
                .withTreasure(treasurePosition, 2)
                .withPlayer(player1)
                .build();

        // Then
        assertThat(treasureMap.getBox(player1.initialPosition()).items()).hasSize(2);
        assertThat(treasureMap.getBox(player1.initialPosition()).items().stream()
                .filter(boxItem -> boxItem instanceof Player)).hasSize(1);

        assertThat(treasureMap.getBox(player1.initialPosition()).items().stream()
                .filter(boxItem -> boxItem instanceof Plain)).hasSize(1);
    } */

   /* @Test
    public void shouldIgnoreAddPlayers_when_boxContainsMountain() {
        // Given
        var mapWidth = 3;
        var mapHeight = 4;
        var mountainPosition = new Position(2, 3);
        var player1 = new Player("Jean", mountainPosition, Orientation.SOUTH,
                List.of(MOVE_FORWARD, TURN_LEFT, TURN_RIGHT));

        // When
        TreasureMap treasureMap = TreasureMap.builder()
                .withWidth(mapWidth)
                .withHeight(mapHeight)
                .withMountain(mountainPosition)
                .withPlayer(player1)
                .build();

        // Then
        assertThat(treasureMap.getBox(player1.initialPosition()).items().stream()
                .filter(boxItem -> boxItem instanceof Player)).hasSize(0);
    }*/

    /*@Test
    public void shouldIgnoreAddPlayers_when_boxContainsTreasure() {
        // Given
        var mapWidth = 3;
        var mapHeight = 4;
        var treasurePosition = new Position(2, 3);
        var player1 = new Player("Jean", treasurePosition, Orientation.SOUTH,
                List.of(MOVE_FORWARD, TURN_LEFT, TURN_RIGHT));

        // When
        TreasureMap treasureMap = TreasureMap.builder()
                .withWidth(mapWidth)
                .withHeight(mapHeight)
                .withTreasure(treasurePosition, 3)
                .withPlayer(player1)
                .build();

        // Then
        assertThat(treasureMap.getBox(player1.initialPosition()).items().stream()
                .filter(boxItem -> boxItem instanceof Player)).hasSize(0);
    }*/

}
