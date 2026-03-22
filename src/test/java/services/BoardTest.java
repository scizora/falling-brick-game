package services;

import model.Brick;
import model.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    void testBoardInitialization() {
        Board board = new Board(5, 5);

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                assertEquals('.', board.grid[r][c]);
            }
        }
    }

    @Test
    void testCanPlaceValidBrick() {
        Board board = new Board(5, 5);
        Brick brick = new Brick();
        brick.setHorizontal();

        assertTrue(board.canPlace(brick));
    }

    @Test
    void testCannotPlaceOutOfBounds() {
        Board board = new Board(5, 5);
        Brick brick = new Brick();
        brick.setHorizontal();

        for (Position p : brick.blocks) {
            p.col = -1;
        }

        assertFalse(board.canPlace(brick));
    }

    @Test
    void testPlaceLockedBrick() {
        Board board = new Board(5, 5);
        Brick brick = new Brick();
        brick.symbols.add('A');
        brick.symbols.add('A');
        brick.symbols.add('A');
        brick.setHorizontal();

        board.placeLockedBrick(brick);

        for (Position p : brick.blocks) {
            assertNotEquals('.', board.grid[p.row][p.col]);
        }
    }

    @Test
    void testCanMoveDown() {
        Board board = new Board(5, 5);
        Brick brick = new Brick();
        brick.setVertical();

        assertTrue(board.canMoveDown(brick));

        for (Position p : brick.blocks) {
            p.row = 4;
        }

        assertFalse(board.canMoveDown(brick));
    }
}