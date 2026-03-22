package model;

import constants.GameConfig;

import java.util.ArrayList;
import java.util.List;

public class Brick {

    public List<Position> blocks = new ArrayList<>();
    public List<Character> symbols = new ArrayList<>();
    public Orientation orientation;

    private List<Position> previousBlocks;

    public Brick() {
        // empty constructor
    }

    // ===== ORIENTATION SETUP =====

    public void setHorizontal() {
        orientation = Orientation.HORIZONTAL;
        blocks.clear();

        int mid = GameConfig.COLS / 2;

        blocks.add(new Position(0, mid - 1));
        blocks.add(new Position(0, mid));
        blocks.add(new Position(0, mid + 1));
    }

    public void setVertical() {
        orientation = Orientation.VERTICAL;
        blocks.clear();

        int mid = GameConfig.COLS / 2;

        blocks.add(new Position(0, mid));
        blocks.add(new Position(1, mid));
        blocks.add(new Position(2, mid));
    }

    // ===== MOVEMENT =====

    public void moveLeft() {
        for (Position p : blocks) {
            p.col--;
        }
    }

    public void moveRight() {
        for (Position p : blocks) {
            p.col++;
        }
    }

    public void moveDown() {
        for (Position p : blocks) {
            p.row++;
        }
    }

    // ===== SAVE / ROLLBACK =====

    public void savePosition() {
        previousBlocks = new ArrayList<>();
        for (Position p : blocks) {
            previousBlocks.add(new Position(p.row, p.col));
        }
    }

    public void rollbackMove(char cmd) {
        if (previousBlocks == null) return;

        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).row = previousBlocks.get(i).row;
            blocks.get(i).col = previousBlocks.get(i).col;
        }
    }
}