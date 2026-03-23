# Match-3 Falling Bricks Game

## Overview

This is a console-based Match-3 falling bricks game implemented in Java.

Players control bricks made up of three symbols and place them on a board to form horizontal or vertical matches of **3 or more identical symbols**. Matching symbols are cleared and points are awarded.

---

## Features

* Horizontal and vertical brick orientations
* Move left (`L`), right (`R`), and drop (`D`)
* Automatic downward movement each turn
* Match detection (3 or more in a row)
* Score tracking system
* Input validation and error handling
* Restart (`S`) or quit (`Q`) functionality
* Unit tests using JUnit

---

## Game Rules

1. The game starts by entering:

   * Field width and height
   * Up to 5 bricks

   Example:

   ```
   5 8 H^^* V@^-
   ```

2. Each brick:

   * Starts at the top of the board
   * Can be horizontal (`H`) or vertical (`V`)
   * Contains exactly 3 symbols

3. Each turn:

   * Player enters up to **2 commands**
   * Only the **first 2 commands are executed**
   * Commands:

     * `L` → Move left
     * `R` → Move right
     * `D` → Drop to bottom

4. After commands:

   * Brick automatically moves down by 1 row

5. When a brick cannot move further:

   * It becomes fixed on the board
   * Matches of **3 or more identical symbols** are cleared
   * Score is updated
   * **No gravity is applied after clearing (as per requirements)**

6. Game ends when:

   * All bricks are placed, OR
   * A new brick cannot be placed at the top

---

## Design

### Architecture Overview

The project follows a clean separation of concerns:
```
        +------------------+
        |   InputHandler   |
        +------------------+
                 |
                 v
        +------------------+
        |       Main       |
        |  (Game Loop)     |
        +------------------+
          |       |       |
          v       v       v
     +--------+ +--------------+ +-------------+
     | Board  | | MatchService | | ScoreService|
     +--------+ +--------------+ +-------------+
          |
          v
     +-----------------------+
     |      Brick            |
     | Position, Orientation |
     +-----------------------+

     +------------------+
     |  BrickFactory    |
     +------------------+
```
### Model Layer

* `Brick` – Represents a moving brick
* `Position` – Represents coordinates (row, column)
* `Orientation` – Enum for horizontal/vertical

### Service Layer

* `Board` – Manages grid state and collision logic
* `BrickFactory` – Parses input and creates brick objects
* `MatchService` – Detects horizontal and vertical matches
* `ScoreService` – Handles scoring logic

### Input Layer

* `InputHandler` – Handles all user input and validation

### Main

* Controls game loop, user input, and game progression

---

## Key Design Decisions

* Separation of concerns between input handling, game logic, and models
* Factory pattern (`BrickFactory`) used to construct bricks cleanly
* Clear game loop structure: input → movement → locking → matching
* Only first 2 commands per turn are executed (remaining ignored)
* Designed for readability and maintainability without overengineering

---

## Assumptions

* Maximum of **5 bricks** per game
* Each brick contains **exactly 3 symbols**
* Matches are **3 or more consecutive identical symbols**
* No gravity is applied after clearing matches
* Invalid commands are ignored
* Input is validated before processing

---

## How to Run

### Requirements

* Java 17+
* Maven 3+

---

### Steps

1. Clone the repository:

```
git clone https://github.com/scizora/falling-brick-game.git
cd falling-brick-game
```

2. Build the project:

```
mvn clean install
```

3. Run the application in git bash:

```
mvn exec:java -Dexec.mainClass="Main"
```

---

### Run Alternative (IDE)

You can also run the program directly from your IDE (e.g., VS Code or IntelliJ) by running the `Main` class. e.g 
via vscode > Run > Start Debugging

If you encounter the error:

Error: Could not find or load main class Main
Caused by: java.lang.ClassNotFoundException: Main

Run the following command in your terminal:

mvn compile

This will compile the project and generate the required .class files in the target/classes directory.
<img width="2975" height="1050" alt="image" src="https://github.com/user-attachments/assets/21030fc3-107d-4c07-8404-a44d87d34b1f" />

---

## Running Tests

```
mvn test
```

---

## Example Input

```
6 8 H&^^ V@*- H###
```

---

## Controls

* `L` → Move left
* `R` → Move right
* `D` → Drop

⚠️ Only the **first 2 commands** per turn are executed
(e.g., `LLD` → only `LL` is applied)

---

## Notes

* Input validation ensures correct format for field size and bricks
* The game handles invalid inputs gracefully
* Restart (`S`) and quit (`Q`) options are available after game ends
* Compiled files (`target/`) are excluded as per submission requirements

---

## Author Notes

This project demonstrates:

* Clean code practices
* Object-oriented design
* Separation of concerns
* Input validation and edge case handling
* Testable and maintainable structure

---

## Future Improvements (Optional)

* Add brick rotation
* Add gravity after clearing matches
* Improve console UI (e.g., colors)
* Enhance scoring system
