package services;

import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreServiceTest {

    private ScoreService scoreService;

    @BeforeEach
    public void setUp() {
        scoreService = new ScoreService();
    }

    @Test
    public void testInitialScoreIsZero() {
        assertEquals(0, scoreService.getScore());
    }

    @Test
    public void testAddScoreSingleMatch() {
        List<Position> matches = Arrays.asList(
                new Position(0, 0),
                new Position(0, 1),
                new Position(0, 2)
        );

        scoreService.addScore(matches);
        assertEquals(30, scoreService.getScore());
    }

    @Test
    public void testAddScoreMultipleMatches() {
        List<Position> matches1 = Arrays.asList(
                new Position(0, 0),
                new Position(0, 1),
                new Position(0, 2)
        );

        List<Position> matches2 = Arrays.asList(
                new Position(1, 0),
                new Position(1, 1)
        );

        scoreService.addScore(matches1);
        scoreService.addScore(matches2);

        assertEquals(50, scoreService.getScore());
    }
}