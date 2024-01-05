package cpsc2150.extendedConnects.cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE

grossmannjenna- Jenna Grossmann
arthorn765 - Abby Thornton
ehanckel - Elle Hanckel

*/

/**
 * @corresponds self = gameBoard[0...MAX_ROW][0...MAX_COL]
 * @invariant [pos below a placed token should not equal " "]
 *
 * GameBoard represents the gameboard for the fast implementation of Connect 4
 */

public class GameBoard extends AbsGameBoard implements IGameBoard {

    public int MAX_ROW;
    public int MAX_COL;
    public int TOKENS_TO_WIN;
    private char[][] gameBoard;

    /**
     * default constructor for the gameboard object. Initializes the gameboard
     * with blank space characters
     *
     * @param userRow the number of rows decided by user that will be assigned to MAX_ROW
     * @param userCol the number of columns decided by user that will be assigned to MAX_COL
     * @param token the number of tokens decided by user that will be assigned to TOKENS_TO_WIN
     * 
     * @pre none
     * @post GameBoard = [0...MAX_ROW][0...MAX_COL] = " "
    */
    public GameBoard(int userRow, int userCol, int token)
    {
        //creating game board
        TOKENS_TO_WIN = token;
        MAX_ROW = userRow;
        MAX_COL = userCol;
        gameBoard = new char[userRow][userCol];
        //nested for loop, looping through rows and column of game board and initializing each position with ' '
        for (int i = 0; i < userRow; i++) {
            for (int j = 0; j < userCol; j++) {
                gameBoard[i][j] = ' ';
            }
        }
    }

    /**
     * used to determine the number of columns in GameBoard
     *
     * @return returns the number of columns in GameBoard
     *
     * @pre none
     * @post getNumColumns = MAX_COL
     */
    @Override
    public int getNumColumns(){
        //returning number of columns
        return MAX_COL;
    };

    /**
     * used to determine the number of rows in GameBoard
     *
     * @return returns the number of rows in GameBoard
     *
     * @pre none
     * @post getNumRow = MAX_ROW
     */
    @Override
    public int getNumRows() {
        //returning number of rows
        return MAX_ROW;
    }

    /**
     * used to determine the number of tokens needed to win the game
     *
     * @return returns the number of tokens needed to win the game
     *
     * @pre none
     * @post getNumToWin = TOKENS_TO_WIN
     */
    @Override
    public int getNumToWin() {
        //returning number of tokens needed to win
        return TOKENS_TO_WIN;
    }

    /**
     * places a token in the next available spot in column c for the player p 
     * 
     * @param p a character that is used as a token
     * @param c an int that describes the column number
     * @pre checkIfFree(c) == true AND c >= 0 AND c < MAX_COL
     * @post [dropToken places the character p in column c at the lowest available row, Gameboard otherwise remains unchanged]
     * AND self = #self
     */
    @Override
    public void dropToken(char p, int c)
    {
        // checking if the column is free
        if (checkIfFree(c)) {
            // looping through the rows starting at the bottom
            for (int i = 0; i < MAX_ROW; i++) {
                // the row has a blank space so add token
                if (gameBoard[i][c] == ' ') {
                    gameBoard[i][c] = p;
                    break; // break out of for loop
                }
            }
        }
    }


    /**
     * returns the player at specified position, if no player marker, returns a blank space char
     * 
     * @pre none 
     * @post whatsAtPos() = [returns the char that represents the player for the given position and a blank space if no player]
     * @post AND self = #self
     * @return  player p that has a token in given position or a blank space if empty
     */
    @Override
    public char whatsAtPos(BoardPosition pos)
    {
        //returning what is at gameBoard
        return gameBoard[pos.getRow()][pos.getColumn()];

    }
}
