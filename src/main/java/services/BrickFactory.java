package services;

import model.Brick;

import java.util.ArrayList;
import java.util.List;

public class BrickFactory {

    public List<Brick> parseBricks(String input) {
        String[] parts = input.split(" ");
        List<Brick> bricks = new ArrayList<>();

        // start from index 2 (after width and height)
        for (int i = 2; i < parts.length; i++) {
            String b = parts[i];

            // safety check (should already be validated in InputHandler)
            if (b.length() != 4) continue;

            char orientationChar = b.charAt(0);
            String symbols = b.substring(1);

            Brick brick = new Brick();

            // ===== SET SYMBOLS =====
            for (char s : symbols.toCharArray()) {
                brick.symbols.add(s);
            }

            // ===== SET ORIENTATION + INITIAL POSITIONS =====
            if (orientationChar == 'H') {
                brick.setHorizontal();   // sets positions
            } else if (orientationChar == 'V') {
                brick.setVertical();     // sets positions
            } else {
                // invalid brick (extra safety)
                continue;
            }

            bricks.add(brick);
        }

        return bricks;
    }
}