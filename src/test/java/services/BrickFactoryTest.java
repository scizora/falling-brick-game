package services;

import model.Brick;
import model.Orientation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BrickFactoryTest {

    @Test
    void testParseSingleHorizontalBrick() {
        BrickFactory factory = new BrickFactory();

        String input = "6 8 HABC";

        List<Brick> bricks = factory.parseBricks(input);

        assertEquals(1, bricks.size());

        Brick brick = bricks.get(0);

        assertEquals(Orientation.HORIZONTAL, brick.orientation);
        assertEquals(3, brick.symbols.size());
        assertEquals('A', brick.symbols.get(0));
        assertEquals('B', brick.symbols.get(1));
        assertEquals('C', brick.symbols.get(2));
    }

    @Test
    void testParseMultipleBricks() {
        BrickFactory factory = new BrickFactory();

        String input = "6 8 HABC VXYZ";

        List<Brick> bricks = factory.parseBricks(input);

        assertEquals(2, bricks.size());

        assertEquals(Orientation.HORIZONTAL, bricks.get(0).orientation);
        assertEquals(Orientation.VERTICAL, bricks.get(1).orientation);
    }

    @Test
    void testEmptyBricks() {
        BrickFactory factory = new BrickFactory();

        String input = "6 8";

        List<Brick> bricks = factory.parseBricks(input);

        assertTrue(bricks.isEmpty());
    }
}