package it.carbon.exercice.treasuremap;

import it.carbon.exercice.treasuremap.application.AppRunner;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
public class IntegrationTest {

    public static final String OUTPUT_TEST_FILE = "output-test-file.txt";
    @Autowired
    AppRunner appRunner;

    @AfterEach
    void tearDown() throws IOException {
        Files.delete(Paths.get("output-test-file.txt"));
    }

    @Test
    public void should_work_with_default_file() throws IOException {
        Path path = Paths.get("output-test-file.txt");
        String actualResult = String.join("\n", Files.readAllLines(path));
        String expectedResult = """
                C - 3 - 4
                M - 1 - 0
                M - 2 - 1
                T - 1 - 3 - 2
                A - Lara - 0 - 3 - S - 3""";
        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void shouldWork_when_OnePlayers() throws Exception {
        // Given
        String inputFilePath = "input/one-player.txt";

        // When
        appRunner.run(inputFilePath);

        // Then
        Path path = Paths.get(OUTPUT_TEST_FILE);
        String actualResult = String.join("\n", Files.readAllLines(path));

        Assertions.assertThat(actualResult).isEqualTo("""
                C - 3 - 4
                M - 1 - 0
                M - 2 - 1
                T - 1 - 3 - 2
                A - Lara - 0 - 3 - S - 3""");
    }

    @Test
    public void shouldWork_when_TwoPlayers() throws Exception {
        // Given
        String inputFilePath = "input/two-player.txt";

        // When
        appRunner.run(inputFilePath);

        // Then
        Path path = Paths.get(OUTPUT_TEST_FILE);
        String actualResult = String.join("\n", Files.readAllLines(path));

        Assertions.assertThat(actualResult).isEqualTo("""
                C - 4 - 5
                M - 1 - 0
                M - 2 - 1
                T - 1 - 3 - 1
                A - Lara - 1 - 2 - S - 1
                A - Tony - 0 - 3 - O - 3""");

    }
}
