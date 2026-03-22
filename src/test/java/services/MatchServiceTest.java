package services;

import constants.GameConfig;
import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MatchServiceTest {

    private MatchService matchService;
    private char[][] grid;

    @BeforeEach
    void setup() {
        matchService = new MatchService();

        GameConfig.ROWS = 5;
        GameConfig.COLS = 5;

        grid = new char[GameConfig.ROWS][GameConfig.COLS];

        for (int r = 0; r < GameConfig.ROWS; r++) {
            for (int c = 0; c < GameConfig.COLS; c++) {
                grid[r][c] = GameConfig.EMPTY_CELL;
            }
        }
    }

    @Test
    void testHorizontalMatch() {
        grid[2][0] = 'A';
        grid[2][1] = 'A';
        grid[2][2] = 'A';

        List<Position> matches = matchService.detectMatches(grid);

        assertEquals(3, matches.size());
    }

    @Test
    void testVerticalMatch() {
        grid[0][1] = 'B';
        grid[1][1] = 'B';
        grid[2][1] = 'B';

        List<Position> matches = matchService.detectMatches(grid);

        assertEquals(3, matches.size());
    }

    @Test
    void testNoMatch() {
        grid[0][0] = 'A';
        grid[0][1] = 'B';
        grid[0][2] = 'C';

        List<Position> matches = matchService.detectMatches(grid);

        assertTrue(matches.isEmpty());
    }
}