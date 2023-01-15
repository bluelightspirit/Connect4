## Connect4
## A Java game based on the game Connect 4 from https://connect4.org/

## How does anyone play the game?

Note the game is designed to be played with two players. However, it is possible to play by yourself as well.
If you are playing with someone else able to access your terminal, it is recommended to play a game such as rock-paper-scissors to determine who is which color (red or green).

First, all 3 files (BoardGame.java, Board.java, BoardInterface.java) must be downloaded all in the same location. Then, all 3 files should be compiled. After that, BoardGame.java can be ran using any IDE such as [IntelliJ](https://www.jetbrains.com/idea/download/) or [BlueJ](https://www.bluej.org/).

Then, to play the game, the AI will randomize what color player 1 and 2 is. If you're playing with someone else, whatever color the AI chose as player 1 should go first. If you're playing by yourself, you can pretend you're going against a mighty enemy (This will only be done at the beginning of the game).

After that, player 1 shall choose an unfilled column between 1-7. Then, whatever column they chose should fill up the board and display that to the user if they chose a valid one. If they did not choose a valid one, they would have a randomized number between 1-3 tries left (also displayed to the user) to choose a valid, unfilled column.

Then, player 2 shall choose an unfilled column between 1-7, just like player 1. Then, whatever column they chose should fill up the board and display that to the user if they chose a valid one. If they did not choose a valid one, they would have a randomized number between 1-3 tries left (also displayed to the user) to choose a valid, unfilled column.

If either player runs out of tries, their turn is skipped. The player tries are reset every time any player's turn begins again.

Player 1 and player 2 will repeat their steps until there is a game decision, where there is a tie, player 1 wins, or player 2 wins.\
Either player 1 or player 2 can win if either of the players reach any 4-character long row diagonally, vertically, or horizontally.\
The game can tie if no player reaches any 4-character long row diagonally, vertically, or horizontally.

## More specifics on the game

If a player presses only the enter key and nothing else, it will count toward their tries.

## How does it work?

It works by using 2 classes and 1 interface and is based on using the terminal which uses `System.out.print` from Java.

> The Board.java class that implements BoardInterface is forced to have the methods set in BoardInterface. Board.java holds a 6x7 2D Array of empty dashes that is changed based on any player's valid move. There is a method that converts that 2D Array to a printable String to the terminal as well. It also handles the calculations if the user does a valid move or not (inserting in a valid not full column), adds in the valid moves to the board, and calculates if there's a winner via upwards diagonal, downwards diagonal, horizontal, or vertical.

> The BoardGame.java class plays the game by randomizing the color player 1 & 2 are (red or green) and works by asking two players individually what column they want to place their chip in. It also gives the player a random amount of tries between 2 and 4 to put in a valid column that does not contain letters, and also calls Board.java to check is full, or to put in a valid number between 1-7 for using up those tries also if any of those are found to be invalid. After giving the player a random amount of tries between 2 and 4, it prints out the current board String from Board.java to the user. Then, it checks for a winner from Board.java and continues all of the steps listed here until there is a winner or a tie.

> The BoardInterface.java interface contains four methods required for any classes that implement it to use, which makes all the board's output into a string, determines if a player's move is valid, determines what to do with the information of a color and column a player is attempting to play with, and checks if there's a winner via upwards diagonal, downwards diagonal, horizontal, or vertical.

## Compiling

This program uses solely Java to compile.
