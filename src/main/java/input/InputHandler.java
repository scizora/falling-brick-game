package input;

import java.util.Scanner;

public class InputHandler {
    private Scanner scanner = new Scanner(System.in);

    public String readInitialInput() {
        while (true) {
            System.out.println("Enter field size (width height) and up to 5 bricks.");
            System.out.println("Example: 5 8 H^^* V@^-");

            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                System.out.println("Input cannot be empty.");
                continue;
            }

            String[] parts = line.split(" ");

            if (parts.length < 2) {
                System.out.println("Invalid format. Please enter width height and bricks.");
                continue;
            }

            // ===== VALIDATE WIDTH / HEIGHT =====
            try {
                int width = Integer.parseInt(parts[0]);
                int height = Integer.parseInt(parts[1]);

                if (width <= 0 || height <= 0) {
                    System.out.println("Width and height must be positive numbers.");
                    continue;
                }

            } catch (NumberFormatException e) {
                System.out.println("Width and height must be numbers.");
                continue;
            }

            // ===== VALIDATE BRICKS =====
            boolean validBricks = true;

            for (int i = 2; i < parts.length; i++) {
                String b = parts[i];

                if (b.length() != 4) {
                    System.out.println("Each brick must be 1 orientation + 3 symbols (e.g., H^^*).");
                    validBricks = false;
                    break;
                }

                char orientation = b.charAt(0);
                if (orientation != 'H' && orientation != 'V') {
                    System.out.println("Brick must start with H or V.");
                    validBricks = false;
                    break;
                }
            }

            if (!validBricks) {
                continue; 
            }

            // ===== MAX 5 BRICKS =====
            if (parts.length - 2 > 5) {
                System.out.println("Maximum 5 bricks allowed.");
                continue;
            }

            return line; 
        }
    }

    public String readCommands() {
        while (true) {
            System.out.print("Enter up to 2 commands (L,R,D): ");
            String line = scanner.nextLine().trim().toUpperCase();

            if (line.isEmpty()) {
                System.out.println("Please enter at least one command.");
                continue;
            }

            boolean valid = true;

            for (char c : line.toCharArray()) {
                if (c != 'L' && c != 'R' && c != 'D') {
                    valid = false;
                    break;
                }
            }

            if (!valid) {
                System.out.println("Invalid command. Use only L, R, D.");
                continue;
            }

            return line; // return full input, don't restrict length
        }
    }

    public String readRestartChoice() {
        while (true) {
            System.out.print("Enter S to start over or Q to quit: ");
            String line = scanner.nextLine().trim().toUpperCase();

            if (line.equals("S") || line.equals("Q")) {
                return line;
            }

            System.out.println("Invalid input. Please enter S or Q.");
        }
    }
}