import constants.GameConfig;
import input.InputHandler;
import model.Brick;
import model.Position;
import model.Orientation;
import services.Board;
import services.BrickFactory;
import services.MatchService;
import services.ScoreService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        InputHandler input = new InputHandler();

        // ===== OUTER LOOP (RESTART GAME) =====
        while (true) {

            // ===== INIT =====
            String init = input.readInitialInput();
            String[] parts = init.split(" ");

            int cols = Integer.parseInt(parts[0]);
            int rows = Integer.parseInt(parts[1]);

            Board board = new Board(rows, cols);

            BrickFactory factory = new BrickFactory();
            List<Brick> templates = factory.parseBricks(init);

            MatchService matchService = new MatchService();
            ScoreService scoreService = new ScoreService();

            int currentBrickIndex = 0;

            if (templates.isEmpty()) {
                System.out.println("No bricks provided.");
                continue;
            }

            Brick brick = createFreshBrick(templates.get(currentBrickIndex));

            // ===== GAME LOOP =====
            while (true) {

                System.out.println("Score: " + scoreService.getScore());
                printBoardWithMovingBrick(board, brick);

                String commands = input.readCommands();

                // TRACK ONLY EXECUTED COMMANDS
                boolean usedDrop = false;

                for (int i = 0; i < Math.min(2, commands.length()); i++) {
                    char cmd = Character.toUpperCase(commands.charAt(i));

                    brick.savePosition();

                    switch (cmd) {
                        case 'L':
                            brick.moveLeft();
                            break;
                        case 'R':
                            brick.moveRight();
                            break;
                        case 'D':
                            usedDrop = true;
                            while (board.canMoveDown(brick)) {
                                brick.moveDown();
                            }
                            break;
                        default:
                            continue;
                    }

                    if (!board.canPlace(brick)) {
                        brick.rollbackMove(cmd);
                    }
                }

                // ===== AUTO DROP =====
                if (!usedDrop && board.canMoveDown(brick)) {
                    brick.moveDown();
                } else {

                    // ===== LOCK BRICK =====
                    board.placeLockedBrick(brick);

                    // ===== MATCH DETECTION =====
                    List<Position> matches = matchService.detectMatches(board.grid);

                    if (!matches.isEmpty()) {
                        scoreService.addScore(matches);
                        matchService.clearMatches(board.grid, matches);
                    }

                    // ===== NEXT BRICK =====
                    currentBrickIndex++;

                    if (currentBrickIndex >= templates.size()) {

                        // show final cleared board
                        System.out.println("Score: " + scoreService.getScore());
                        board.printBoard();

                        System.out.println("Game Over.");
                        break;
                    }

                    brick = createFreshBrick(templates.get(currentBrickIndex));

                    // ===== GAME OVER =====
                    if (!board.canPlace(brick)) {
                        board.printBoard();
                        System.out.println("Game Over.");
                        break;
                    }
                }
            }

            // ===== RESTART / QUIT =====
            while (true) {
                String choice = input.readRestartChoice();

                if (choice.equals("S")) {
                    break;
                } else if (choice.equals("Q")) {
                    System.out.println("Thank you for playing Match-3!");
                    return;
                } else {
                    System.out.println("Invalid input.");
                }
            }
        }
    }

    // ===== CREATE FRESH BRICK =====
    private static Brick createFreshBrick(Brick template) {
        Brick brick = new Brick();

        brick.symbols.addAll(template.symbols);

        if (template.orientation == Orientation.HORIZONTAL) {
            brick.setHorizontal();
        } else {
            brick.setVertical();
        }

        return brick;
    }

    // ===== DISPLAY =====
    private static void printBoardWithMovingBrick(Board board, Brick brick) {
        for (int r = 0; r < GameConfig.ROWS; r++) {

            System.out.print("| ");

            for (int c = 0; c < GameConfig.COLS; c++) {

                char symbol = board.grid[r][c];

                for (Position p : brick.blocks) {
                    if (p.row == r && p.col == c) {
                        int index = brick.blocks.indexOf(p);
                        symbol = brick.symbols.get(index);
                        break;
                    }
                }

                System.out.print(symbol + " ");
            }

            System.out.println("|");
        }
    }
}