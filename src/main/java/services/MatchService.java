package services;

import constants.GameConfig;
import model.Position;

import java.util.ArrayList;
import java.util.List;

public class MatchService {

    public List<Position> detectMatches(char[][] grid) {
        List<Position> matches = new ArrayList<>();

        // horizontal matches
        for (int r = 0; r < GameConfig.ROWS; r++) {
            for (int c = 0; c <= GameConfig.COLS - GameConfig.MATCH_COUNT; c++) {
                char symbol = grid[r][c];
                if (symbol == GameConfig.EMPTY_CELL) continue;
                boolean match = true;
                for (int i = 1; i < GameConfig.MATCH_COUNT; i++) {
                    if (grid[r][c + i] != symbol) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    for (int i = 0; i < GameConfig.MATCH_COUNT; i++)
                        matches.add(new Position(r, c + i));
                }
            }
        }

        // vertical matches
        for (int c = 0; c < GameConfig.COLS; c++) {
            for (int r = 0; r <= GameConfig.ROWS - GameConfig.MATCH_COUNT; r++) {
                char symbol = grid[r][c];
                if (symbol == GameConfig.EMPTY_CELL) continue;
                boolean match = true;
                for (int i = 1; i < GameConfig.MATCH_COUNT; i++) {
                    if (grid[r + i][c] != symbol) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    for (int i = 0; i < GameConfig.MATCH_COUNT; i++)
                        matches.add(new Position(r + i, c));
                }
            }
        }

        return matches;
    }

    public void clearMatches(char[][] grid, List<Position> matches) {
        for (Position p : matches) {
            grid[p.row][p.col] = GameConfig.EMPTY_CELL;
        }
    }
}