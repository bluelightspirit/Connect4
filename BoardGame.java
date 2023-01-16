import java.util.Scanner;
/**
 * Board
 * <p>
 *
 * @author Gary Young
 * Original version: 11/29/2022
 * @version 01/16/2023
 * BoardGame plays the Connect4 game from Board
 */

public class BoardGame {
    public static void main(String[] args) {
        // print board dashes & columns
        BoardInterface board = new Board();
        String player1Color;
        String player2Color;
        int playerColorRed = 1;
        int playerColorGreen = 2;
        // randomize color of players
            int playerColorRNG = (int) (Math.random()*2); // 0 to 1
            if (playerColorRNG==1) {
                player1Color = "G";
                player2Color = "R";
                playerColorGreen = 1;
                playerColorRed = 2;
            }
            else {
                player1Color = "R";
                player2Color = "G";
            }
            Board.turn = player1Color;
        System.out.printf("PLEASE NOTE: The first player (Color %s) will always play first!\n", player1Color);
        boolean isWinner = false;
        String winner;
        System.out.printf("Player 1 will be %s\n", player1Color);
        System.out.printf("Player 2 will be %s\n", player2Color);
        // create scanner for column #
        Scanner cIn = new Scanner(System.in);
        while (!isWinner) {
            // line filler to be equivalent to receiving error or termination message
            // prints before print of current board data
            // winner/tie has a different print before board data after while (!isWinner)
            if (Board.moveValid){
                System.out.println(">VALID\n>MOVE\n>DETECTED!\n>THANK YOU!");
            }
            // give player 1 or 2 a number between 2 and 4 tries to enter a correct column
            if (Board.numTries == 0) {
                Board.numTries = (int) (Math.random() * 3) + 2; // give player 2 to 4 tries
            }
            System.out.println(board);
            if (Board.turn.equals(player1Color)) {
                if (Board.numTries > 1) {
                    System.out.printf("Player 1's turn! (%s) — %d tries remaining to use this turn to enter a valid column\n", player1Color, Board.numTries);
                }
                else {
                    System.out.printf("Player 1's turn! (%s) — %d try remaining to use this turn to enter a valid column\n", player1Color, Board.numTries);
                }
            }
            else if (Board.turn.equals(player2Color)) {
                if (Board.numTries > 1) {
                    System.out.printf("Player 2's turn! (%s) — %d tries remaining to use this turn to enter a valid column\n", player2Color, Board.numTries);
                }
                else {
                    System.out.printf("Player 2's turn! (%s) — %d try remaining to use this turn to enter a valid column\n", player2Color, Board.numTries);
                }
            }
            System.out.println("Enter column (1-7) to place your chip in > ");
            int col;
            // initialize a variable = to next line of scanner input of user
            String colSearch = cIn.nextLine();
            // if player inserts a valid column, play
            // if invalid, subtract numTries until player 1 or 2's turn is given up by numTries = 0
            try {
                // check if player entered a valid number
                col = Integer.parseInt(colSearch);
                // play (which checks if player entered a valid number between 1-7 also)
                board.play(col,Board.turn);
            }
            // determine if player can attempt more tries to enter a valid column
            // if player cannot enter another column, switch turns
            // this catch checks numTries via if the number inputted is NOT an integer
            catch (NumberFormatException e) {
                Board.numTries--;
                Board.moveValid=false;
                if (Board.numTries >= 2){
                    System.out.printf(">Warning to player %s!!!\n>Error: Input of \"%s\" is NOT an integer!\n>You have %d tries remaining\n>Please select a non-full column from 1-7!\n", Board.turn, colSearch, Board.numTries);
                }
                else if (Board.numTries == 1) {
                    System.out.printf(">Warning to player %s!!!\n>Error: Input of \"%s\" is NOT an integer!\n>You have %d try remaining\n>Please select a non-full column from 1-7!\n", Board.turn, colSearch, Board.numTries);
                }
                else if (Board.numTries == 0) {
                    System.out.printf(">Turn termination notice to player %s!!!\n>Error: Input of \"%s\" is NOT an integer!\n>You have %d tries remaining\n>Player %s's turn is now skipped. Please select a valid & available column next time!\n", Board.turn, colSearch, Board.numTries, Board.turn);
                    if (Board.turn.equals("R")) {
                        Board.turn = "G";
                    } else {
                        Board.turn = "R";
                    }
                }
            }
            // check if there is a winner or a tie
            winner = board.checkWinner();
            // winner is R [Red]
            switch (winner) {
                case "R" -> {
                    System.out.printf(">A WINNER\n>HAS BEEN\n>DETECTED!\n>Congratulations, %s [Red] (Player %d)!\n", winner, playerColorRed);
                    System.out.println(board);
                    System.out.printf("The winner is %s [Red] (Player %d)!!!", winner, playerColorRed);
                    isWinner = true;
                }
                // winner is G [Green]
                case "G" -> {
                    System.out.printf(">A WINNER\n>HAS BEEN\n>DETECTED!\n>Congratulations, %s [Green] (Player %d)!\n", winner, playerColorGreen);
                    System.out.println(board);
                    System.out.printf("The winner is %s [Green] (Player %d)!!!", winner, playerColorGreen);
                    isWinner = true;
                }
                // tie between R [Red] & G [Green]
                case "T" -> {
                    System.out.printf(">A TIE\n>HAS BEEN\n>DETECTED!\n>R [Red] (Player %d) and G [Green] (Player %d) are now in stalemate.\n", playerColorRed, playerColorGreen);
                    System.out.println(board);
                    System.out.printf("The game is tied [Red (Player %d) & Green (Player %d) Stalemate]", playerColorRed, playerColorGreen);
                }
            }
        }
        // close scanner after done scanning for input from user
        cIn.close();
    }
}