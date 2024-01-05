package cpsc2150.extendedConnects;
import cpsc2150.extendedConnectX.models.BoardPosition;
import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.GameBoardMem;
import cpsc2150.extendedConnectX.models.IGameBoard;

import java.io.ObjectInput;
import java.util.Objects;
import java.util.Scanner;

//import static cpsc2150.extendedConnectX.models.GameBoard.MAX_COL;
//import static cpsc2150.extendedConnectX.models.GameBoard.MAX_ROW;
import static java.lang.System.exit;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE

grossmannjenna- Jenna Grossmann
arthorn765 - Abby Thornton
ehanckel - Elle Hanckel

*/

/**
 * This is a class for Gamescreen. It prompts the user to play Connect 5 and allows them to choose
 * how many players, their corresponding characters, the size of the board, the number needed to win, and
 * a fast game or memory efficient game.
 * It uses other classes to print the gameBoard, to keep track
 * of tokens dropped and determined if someone has won. It validates
 * user input and asks user if they want to play again.
 */


public class GameScreen {
    public static int MAX_ROWS = 100;
    public static int MAX_COLS = 100;
    public static int MIN_ROWS = 3;
    public static int MIN_COLS = 3;
    public static int MAX_TOKENS = 25;
    public static int MIN_TOKENS = 3;

    public static int MIN_PLAYERS = 2;
    public static int MAX_PLAYERS = 10;
    private static IGameBoard game;

    /**
     * verifies the given column location is valid, and if not, continues asking for a new
     * column until it is valid and then returns the valid column number
     *
     * @param game the 2d array or map for gameboard from interface
     * @param obj the scanner for printing to the system
     * @param col column number to be checked
     * @param player player whose input is being validated
     *
     * @pre none
     * @post 0 < col > MAX_COL
     * @return a valid column number to place token in
     */
    private static int validateLoc(IGameBoard game, Scanner obj, int col, char player){
        //checking if col is out of range of game board size, and prompting new location until valid
        while (col < 0 || col > game.getNumColumns() - 1) {

            //if col is less than 0, prints invalid location
            if (col < 0) {
                System.out.println("Column cannot be less than 0");

            //if col is greater than 6, prints invalid location
            } else {
                System.out.println("Column cannot be greater than " + (game.getNumColumns() - 1));
            }
            //prompting for new column number
            System.out.println("Player " + player + ", what column do you want to place your marker in?");
            col = obj.nextInt();
        }
        //returning valid column
        return col;
    }
    public static void main(String[] args)
    {
        //variables needed to run main
        char[] playerChar;
        int rows;
        int columns;
        int tokens;
        char gameType;


        int col;
        int players;
        boolean over = false;
        char winner = ' ';
        boolean playAgain = true;

        //continue to play until user indicates they want to stop
        while (playAgain) {
            //creating Scanner and GameBoard object
            Scanner obj = new Scanner(System.in);

            //asking how many players they want and scanning in
            System.out.println("How many players?");
            players = obj.nextInt();

            //validating players is greater than 2 and less than 10
            while (players < MIN_PLAYERS || players > MAX_PLAYERS) {
                if (players < MIN_PLAYERS) {
                    System.out.println("Must be at least 2 players");
                    System.out.println("How many players?");
                    players = obj.nextInt();
                }
                if (players > MAX_PLAYERS) {
                    System.out.println("Must be 10 players or fewer");
                    System.out.println("How many players?");
                    players = obj.nextInt();
                }
            }

            //array of char for each player's token
            playerChar = new char[players];
            char input;

            //looping through array with the size of the amount of players
            for (int i = 0; i < players; i++) {
                //reading in each character
                System.out.println("Enter the character to represent player " + (i + 1));
                input = obj.next().charAt(0);
                //making character upper case if not already
                if (Character.isLowerCase(input)) {
                    input = Character.toUpperCase(input);
                }
                //looping through array backwards to make sure the token selected is not already taken
                for (int j = i; j >= 0; j--) {
                    if (playerChar[j] == input) {
                        System.out.println(input + " is already taken as a player token!");
                        System.out.println("Enter the character to represent player " + (i + 1));
                        input = obj.next().charAt(0);
                        if (Character.isLowerCase(input)) {
                            input = Character.toUpperCase(input);
                        }
                    }
                }
                playerChar[i] = input;
            }

            //asking how many rows the user wants
            System.out.println("How many rows should be on the board?");
            rows = obj.nextInt();

            //validating rows is not less than 0 or greater than 100
            while (rows < MIN_ROWS || rows > MAX_ROWS) {
                if (rows < MIN_ROWS) {
                    System.out.println("Must be at least 1 row");
                    System.out.println("How many rows should be on the board?");
                    rows = obj.nextInt();
                }
                if (rows > MAX_ROWS) {
                    System.out.println("Must be 100 rows or fewer");
                    System.out.println("How many rows should be on the board?");
                    rows = obj.nextInt();
                }
            }

            //asking how many columns the user want
            System.out.println("How many columns should be on the board?");
            columns = obj.nextInt();

            //validating columns is not less than 0 or greater than 100
            while (columns < MIN_COLS || columns > MAX_COLS) {
                if (columns < MIN_COLS) {
                    System.out.println("Must be at least 1 columns");
                    System.out.println("How many columns should be on the board?");
                    columns = obj.nextInt();
                }
                if (columns > MAX_COLS) {
                    System.out.println("Must be 100 columns or fewer");
                    System.out.println("How many columns should be on the board?");
                    columns = obj.nextInt();
                }
            }

            //asking how many tokens need to win that the user wants
            System.out.println("How many in a row to win?");
            tokens = obj.nextInt();

            //validating the number of tokens to win is not less than 3 or greater than 25
            while (tokens < MIN_TOKENS || tokens > MAX_TOKENS) {
                if (tokens < MIN_TOKENS) {
                    System.out.println("Must be at least 3 tokens");
                    System.out.println("How many in a row to win?");
                    tokens = obj.nextInt();
                }
                if (tokens > MAX_TOKENS) {
                    System.out.println("Must be 25 tokens or fewer");
                    System.out.println("How many in a row to win?");
                    tokens = obj.nextInt();
                }
            }

            //asking what type of game the user wants to play
            System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
            gameType = obj.next().charAt(0);

            // validating the type of game user chose
            while (gameType != 'F' && gameType != 'f' && gameType != 'M' && gameType != 'm') {
                System.out.println("Please enter F or M");
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
                gameType = obj.next().charAt(0);
            }


            // if players chooses Fast Game
            if (gameType == 'F' || gameType == 'f') {
                //create game board
                game = new GameBoard(rows, columns, tokens);
            }
            // if players chooses the memory efficient game
            else {
                //create game board
                game = new GameBoardMem(rows, columns, tokens);
            }


            //printing game board
            System.out.println(game.toString());

            // loop to go between each player
            while (!over) {
                for (int i = 0; i < players; i++) {
                    //prompting first player for column number and validating column
                    System.out.println("Player " + playerChar[i] + ", what column do you want to place your marker in?");
                    col = obj.nextInt();

                    // validates column
                    col = validateLoc(game, obj, col, playerChar[i]);

                    //while loop, asking user for new column if the one they entered is full and validating it
                    while (!game.checkIfFree(col)) {
                        System.out.println("Column is full");
                        System.out.println("Player " + playerChar[i] + ", what column do you want to place your marker in?");

                        col = obj.nextInt();
                        col = validateLoc(game, obj, col, playerChar[i]);


                    }

                    // player wants to drop their token in a free column
                    if (game.checkIfFree(col)) {
                        game.dropToken(playerChar[i], col);

                        //printing out board with new dropped token
                        System.out.println(game.toString());

                        //checking to see if it resulted in a win
                        if (!game.checkForWin(col)) {
                            over = false;
                        } else {
                            over = true;
                            winner = playerChar[i];
                            break;
                        }

                        //checking to see if it resulted in a tie
                        if (game.checkTie()) {
                            System.out.println("It's a tie!");
                            over = true;
                            break;
                        }
                    }
                }
            }


            // finished the game, asking if player wants to play another game
            if (game.checkTie()) {
                System.out.println("No player won!\nWould you like to play again? Y/N");
            }
            else {
                System.out.println("Player " + winner + " won!\nWould you like to play again? Y/N");
            }

            // getting input from user
            Scanner scanner = new Scanner(System.in);
            char userInput = scanner.next().charAt(0);

            // validating input from user
            while (userInput != 'Y' && userInput != 'y' && userInput != 'N' && userInput != 'n') {
                System.out.println("Would you like to play again? Y/N");
                userInput = scanner.next().charAt(0);
            }

            // playing again decision based on input from user
            if (userInput == 'y' || userInput == 'Y') {
                // playAgain continues to be true
                over = false;
            }
            else {
                playAgain = false;
            }

        }
    }

}