package services;

import constants.GameConfig;
import model.Brick;
import model.Position;

public class Board {

    public char[][] grid;

    public Board(int rows, int cols) {
        GameConfig.ROWS = rows;
        GameConfig.COLS = cols;

        grid = new char[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = GameConfig.EMPTY_CELL;
            }
        }
    }

    // ===== CHECK IF BRICK CAN BE PLACED =====
    public boolean canPlace(Brick brick) {
        for (Position p : brick.blocks) {

            // bounds check
            if (p.col < 0 || p.col >= GameConfig.COLS ||
                p.row < 0 || p.row >= GameConfig.ROWS) {
                return false;
            }

            // ONLY check collision with locked grid
            if (grid[p.row][p.col] != GameConfig.EMPTY_CELL) {
                return false;
            }
        }
        return true;
    }

    // ===== CHECK IF BRICK CAN MOVE DOWN =====
    public boolean canMoveDown(Brick brick) {
        for (Position p : brick.blocks) {

            int newRow = p.row + 1;

            if (newRow >= GameConfig.ROWS) {
                return false;
            }

            if (grid[newRow][p.col] != GameConfig.EMPTY_CELL) {
                return false;
            }
        }
        return true;
    }

    // ===== LOCK BRICK INTO GRID =====
    public void placeLockedBrick(Brick brick) {
        for (int i = 0; i < brick.blocks.size(); i++) {
            Position p = brick.blocks.get(i);
            grid[p.row][p.col] = brick.symbols.get(i);
        }
    }

    // ===== PRINT BOARD =====
    public void printBoard() {
        for (int r = 0; r < GameConfig.ROWS; r++) {

            System.out.print("| ");

            for (int c = 0; c < GameConfig.COLS; c++) {
                System.out.print(grid[r][c] + " ");
            }

            System.out.println("|");
        }
    }
}