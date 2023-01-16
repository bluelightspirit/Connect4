/**
 * Board
 * <p>
 *
 * @author Gary Young
 * @description Original version: 11/29/2022
 * @version 01/15/2022
 * Board sets up the board for Connect4.
 */
public class Board implements BoardInterface {
    public Board() {
        // the initial blanks set for the connect4 data 2D array (data[][]) itself
        // this makes all data[0-5][0-6] equivalent to "-" initially
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                data[row][col] = "-";
            }
        }
    }

    String[][] data = new String[6][7];
    // tells player error if column is full, otherwise say out of bounds
    public boolean columnFull = false;
    // these are static for the purpose of BoardGame
    // since numTries is used to count down how many tries the player has left
    // until their turn is skipped
    // and since turn is how turns can be switched inside of Board and BoardGame
    // and since moveValid is used as a line filler for BoardGame
    public static int numTries = 0; // randomized in beginning of BoardGame's (while !isWinner)
    public static String turn = ""; // turn is switched in Board.play() & BoardGame's catch statement
    public static boolean moveValid = false; // determines if previous move was valid for BoardGame to add in filler lines that the warnings would usually take up
    // this method is how the current board can be displayed to the players
    // since it returns a String (as str)
    public String toString() {
        // initializes string as str
        String str = "";
        // searches the Board data 2D array (data[][])
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                // converts the board data 2D array (data[][]) into a String (as str)
                // based upon what is searched from the board data 2D array (data[][])
                // adds the characters
                str = str.concat(data[row][col]);
                // adds a space after every character
                str = str.concat(" ");
            }
            // new line for new row of characters
            str = str.concat("\n");
        }
        // adds bottom column numbers
        str += "1 2 3 4 5 6 7";
        // returns current board data to player as a String
        return str;
    }
    // this method determines if a user's move is valid in terms of int
    // in terms of not being an int, refer to BoardGame's try catch as a String will be searched first
    public boolean isValidMove(int column) {
        moveValid=true;
        column--;
        // returns false if column is <1 or >7
        if (column < 0 || column > 6) {
            moveValid = false;
        }
        // returns false if column is full
        else if (!(data[0][column].equals("-"))) {
            columnFull=true;
            moveValid = false;
        }
        return moveValid;
    }
    // this is one way where numTries can decrease due to invalid column
    // this performs the application of isValidMove(int column)
    // after that, if the move is valid, insert chip into the lowest row
    // finally, switch turns
    // note: rows are in reverse order of the actual Connect4 game
    // where row 0 (1 to the player) is the topmost row
    // and where row 5 (6 to the player) is the bottommost row
    public boolean play(int column, String color) {
        int chipInsertCounter = 5;
        boolean move = true;
        // if player inserts invalid column, make player not able to play
        if (!isValidMove(column)){
            numTries--;
            // tell player error is columnFull
            if (columnFull) {
                if (numTries >= 2) {
                    System.out.printf(">Warning to player %s!!!\n>Error: Column %s entered is full!\n>You have %d tries remaining\n>Please select a non-full column from 1-7!\n", turn, column, numTries);
                } else if (numTries == 1) {
                    System.out.printf(">Warning to player %s!!!\n>Error: Column %s entered is full!\n>You have %d try remaining\n>Please select a non-full column from 1-7!\n", turn, column, numTries);
                } else { // switch turns if player runs out of tries
                    System.out.printf(">Turn termination notice to player %s!!!\n>Error: Column %s entered is full!\n>You have %d tries remaining\n>Player %s's turn is now skipped. Please select a valid & available column next time!\n", turn, column, numTries, turn);
                    if (turn.equals("R")) {
                        turn = "G";
                    } else {
                        turn = "R";
                    }
                    move = false;
                }
                // set columnFull to false again
                columnFull=false;
            }
            // tell player error is column is outside what can be chosen
            else {
                if (numTries >= 2) {
                    System.out.printf(">Warning to player %s!!!\n>Error: Column %s entered is out of bounds (not between 1-7)!\n>You have %d tries remaining\n>Please select a non-full column from 1-7!\n", turn, column, numTries);
                } else if (numTries == 1) {
                    System.out.printf(">Warning to player %s!!!\n>Error: Column %s entered is out of bounds (not between 1-7)!\n>You have %d try remaining\n>Please select a non-full column from 1-7!\n", turn, column, numTries);
                } else { // switch turns if player runs out of tries
                    System.out.printf(">Turn termination notice to player %s!!!\n>Error: Column %s entered is out of bounds (not between 1-7)!\n>You have %d tries remaining\n>Player %s's turn is now skipped. Please select a valid & available column next time!\n", turn, column, numTries, turn);
                    if (turn.equals("R")) {
                        turn = "G";
                    } else {
                        turn = "R";
                    }
                    move = false;
                }
            }
        }
        // check which row player chip goes
        else {
            column--;
            for (int row = 5; row > 0; row--) {
                if (!(data[row][column].equals("-"))) {
                    chipInsertCounter--;
                }
            }
            // insert chip
            data[chipInsertCounter][column] = color;
            // reset numTries random number generator after valid play
            numTries=0;
            // switch turns
            if (turn.equals("R")) {
                turn = "G";
            } else {
                turn = "R";
            }
        }
        // return boolean value at end
        return move;
    }
    // returns winner if 4 is connected by either player or if there is a tie
    public String checkWinner() {
        String winner = "-";
        // horizontal check
        for (int row = 0; row <= 5; row++) {
            for (int col = 0; col <= 3; col++) {
                    if (!(data[row][col].equals("-"))
                            && data[row][col].equals(data[row][col+1])
                            && data[row][col].equals(data[row][col+2])
                            && data[row][col].equals(data[row][col+3])) {
                        winner = data[row][col];
                        return winner;
                }

            }
        }
        // vertical check
        for (int row = 0; row <= 2; row++) {
            for (int col = 0; col <= 6; col++) {
                    if (!(data[row][col].equals("-"))
                            && data[row][col].equals(data[row+1][col])
                            && data[row][col].equals(data[row+2][col])
                            && data[row][col].equals(data[row+3][col])) {
                        winner = data[row][col];
                        return winner;
                    }
            }
        }
        // diagonal upward check
        for (int row = 3; row <= 5; row++) {
            for (int col = 0; col <= 3; col++) {
                    if (!(data[row][col].equals("-"))
                            && data[row][col].equals(data[row-1][col+1])
                            && data[row][col].equals(data[row-2][col+2])
                            && data[row][col].equals(data[row-3][col+3])) {
                        winner = data[row][col];
                        return winner;

                    }

            }
        }
        // diagonal downward check
        for (int row = 0; row <= 2; row++) {
            for (int col = 0; col <= 3; col++) {
                if (!(data[row][col].equals("-"))
                        && data[row][col].equals(data[row+1][col+1])
                        && data[row][col].equals(data[row+2][col+2])
                        && data[row][col].equals(data[row+3][col+3])) {
                    winner = data[row][col];
                    return winner;
                }
            }
        }
        // tie check
        int tieCheckCounter=0;
        for (int col = 0; col <= 6; col++){
            if (!(data[0][col].equals("-"))){
                tieCheckCounter++;
            }
        }
        if (tieCheckCounter == 7){
            winner = "T";
        }
        return winner;
        }
    }