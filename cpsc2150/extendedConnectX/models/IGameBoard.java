package cpsc2150.extendedConnects.cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE

arthorn765 - Abigail Thornton
grossmannjenna - Jenna Grossmann
ehanckel - Elle Hanckel

*/

/**
 * GameBoard represents a grid of characters.
 * Indexing starts at 0.
 * 
 * initialization ensures: [self initializes as a gameboard with only blank space characters]
 *
 * Constraints: [pos below a placed token should not equal ' ']
 */

public interface IGameBoard {
    /**
     * used to determine the number of rows in GameBoard
     *
     * @return returns the number of rows in GameBoard
     *
     * @pre none
     * @post getNumRow = MAX_ROW
     */
    public int getNumRows();

    /**
     * used to determine the number of columns in GameBoard
     *
     * @return returns the number of columns in GameBoard
     *
     * @pre none
     * @post getNumColumns = MAX_COL
     */

    public int getNumColumns();

    /**
     * used to determine the number of tokens needed to win the game
     *
     * @return returns the number of tokens needed to win the game
     *
     * @pre none
     * @post getNumToWin = TOKENS_TO_WIN
     */

    public int getNumToWin();

    /**
     * places a token in the next available spot in column c for the player p
     *
     * @param p a character that is used as a token
     * @param c an int that describes the column number
     * @pre checkIfFree(c) == true AND c >= 0 AND c < MAX_COL
     * @post [dropToken places the character p in column c at the lowest available row, Gameboard otherwise remains unchanged]
     * AND self = #self
     */
    public void dropToken(char p, int c);

    /**
     * returns the player at specified position, if no player marker, returns a blank space char
     *
     * @pre none
     * @post whatsAtPos() = [returns the char that represents the player for the given position and a blank space if no player]
     * @post AND self = #self
     * @return  player p that has a token in given position or a blank space if empty
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * returns a boolean that checks if a space is free in the specified column number
     * @param c the column number to be checked
     * @pre  c >= 0 AND c < MAX_COL
     * @post [returns true if the top position in the gameboard at column c equals ' ', otherwise false]
     * AND self = #self
     * @return a bool that is true if position is unoccupied and false otherwise
     */
    default public boolean checkIfFree(int c) {
        //returns true if the column can accept another token; false otherwise
        BoardPosition pos = new BoardPosition(getNumRows() - 1, c);

        // checking if there is an empty space at the top of the column
        if (whatsAtPos(pos) == ' ') {
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * returns a boolean that checks if last token placed in column c resulted in a win
     *
     * @param c the int of the column number to be checked
     * @pre c >= 0 AND c < MAX_COL AND [c is the column where the latest token was placed]
     * @post checkForWin = (checkHorizWin == true) OR (checkVertWin == true) OR (checkDiagWin == true) AND self = #self
     * @return true if column c contains a win, false if not
     */
    default public boolean checkForWin(int c) {

        char p;
        int tokenRow = 0;

        //looping through rows, checking if not free
        for (int i = getNumRows() - 1; i > 0; i--) {
            if(!checkIfFree(c)) {
                break;
            }
            //creating board position object with row being checked
            BoardPosition iterPos = new BoardPosition(i-1, c);
            //getting token in row
            if(whatsAtPos(iterPos) != ' ') {
                tokenRow = iterPos.getRow();
                break;
            }
        }

        // checking token row of column c
        BoardPosition pos = new BoardPosition(tokenRow, c);
        p = whatsAtPos(pos);

        // checking for win
        return (checkHorizWin(pos, p) || checkDiagWin(pos, p) || checkVertWin(pos, p));
    }


    /**
     * returns a boolean that checks if there are any available spots left on the board,
     * resulting in a tie if the board is full.
     *
     * @pre none
     * @post checkTie() = [a bool that is true if there are no free board positions remaining and false if there are]
     * AND self = #self
     * @return true if the game is tied and false otherwise
     */
    default public boolean checkTie() {
        // checks to see if the whole game board is full
        for (int i = 0; i < getNumColumns(); i++) {
            if (checkIfFree(i)) {
                return false;
            }
            if (checkForWin(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * returns a boolean that checks if last token placed in position by player p
     * resulted in number in a row needed to have a horizontal win
     *
     * @param pos: token position for last token placed
     * @param p: player the token was placed by
     * @pre p != ' '
     * @post checkHorzWin() = [true if pos resulted in a row having target number consecutive horizontal tokens by player p, false if not]
     * AND self = #self
     * @return true if position resulted in a horizontal win, false otherwise
     */
    default public boolean checkHorizWin(BoardPosition pos, char p) {
        // initialization of variables
        int newRow = pos.getRow();
        int total = 0;

        // checking for same player tokens to the right of the column
        for (int i = 0; i < getNumColumns(); i++) {
            BoardPosition newPos = new BoardPosition(newRow, i);
            // check if the same play is at this new Position
            if (whatsAtPos(newPos) == p) {
                total++;
                if (total == getNumToWin()) {
                    return true;
                }
            }
            else { // it's not consecutive so restart counter
                total = 0;
            }
        }
        // no win
        return false;
    };

    /**
     * returns a boolean that checks if last token placed in position by player p
     * resulted in number in a row needed to have a vertical win
     *
     * @param pos: token position for last token placed
     * @param  p: player the token was placed by
     * @pre p != ' '
     * @post checkVertWin() = [true if pos resulted in column having target number of consecutive vertical tokens by player p, false if not]
     * AND self = #self
     * @return true if position resulted in a vertical win, false otherwise
     */
    default public boolean checkVertWin(BoardPosition pos, char p) {

        // initializing variables
        int newCol = pos.getColumn();
        int total = 0;

        // checking for same player tokens below the dropped Token
        for (int i = 0; i < getNumRows(); i++) {
            BoardPosition newPos = new BoardPosition(i, newCol);
            // check if the same play is at this new Position
            if (whatsAtPos(newPos) == p) {
                total++;
                if (total == getNumToWin()) {
                    return true;
                }
            }
            else { // it's not consecutive so restart counter
                total = 0;
            }
        }
        // no win
        return false;
    };

    /**
     * returns a boolean that checks if last token placed in position by player p
     * resulted in number needed in a row to have a diagonal win
     *
     * @param pos: token position for last token placed
     * @param  p: player the token was placed by
     * @pre p != ' '
     * @post checkDiagWin() = [true if pos has target number of consecutive tokens on either diagonal by player p, false if not] AND self = #self
     * @return true if position resulted in a diagonal win, false otherwise
     */
    default public boolean checkDiagWin(BoardPosition pos, char p) {

        // initializing variables
        int newRow = pos.getRow();
        int newCol = pos.getColumn();

        //right diagonal
        int right = 1;

        //looping through to board to check for a right diagonal win
        for (int i = 1; i <= getNumToWin(); i++) {
            if (newRow + i < getNumRows() && newCol - i >= 0) {

                //looking at bottom half of diagonal
                BoardPosition newPos = new BoardPosition(newRow + i, newCol - i);

                //keeping track of number of tokens in bottom half
                if (whatsAtPos(newPos) == p) {
                    right++;
                    if (right == getNumToWin()) {
                        return true;
                    }
                }
            }
        }

        //looping through to board to check for a right diagonal win
        for (int j = 1; j <= getNumToWin(); j++) {
            if (newRow - j >= 0 && newCol + j < getNumColumns()) {

                //looking at top half of right diagonal
                BoardPosition newPos = new BoardPosition(newRow - j, newCol + j);

                //keeping track of tokens in top half
                if (whatsAtPos(newPos) == p) {
                    right++;
                    if (right == getNumToWin()) {
                        return true;
                    }
                }
            }
        }

        //left diagonal
        int left = 1;

        //looping through to board to check for a left diagonal win
        for (int k = 1; k <= getNumToWin(); k++) {
            if (newRow - k >= 0 && newCol - k >= 0) {

                //looking at top half of left diagonal
                BoardPosition newPos = new BoardPosition(newRow - k, newCol - k);

                //keeping track of tokens in top half
                if (whatsAtPos(newPos) == p) {
                    left++;
                    if (left == getNumToWin()) {
                        return true;
                    }
                }
            }
        }

        //looping through to board to check for a left diagonal win
        for (int l = 1; l <= getNumToWin(); l++) {
            if (newRow + l < getNumRows() && newCol + l < getNumColumns()) {

                //looking at bottom half of left diagonal
                BoardPosition newPos = new BoardPosition(newRow + l, newCol + l);

                //keeping track of tokens in bottom half
                if (whatsAtPos(newPos) == p) {
                    left++;
                    if (left == getNumToWin()) {
                        return true;
                    }
                }
            }
        }
        return false;

    }


    /**
     * returns a boolean that checks if the specified player has a marker at the given postiion
     *
     * @param pos board position to check
     * @param player character that identifies the player
     * @pre player != ' '
     * @post isPlayerAtPost() = [returns true if the player is at pos, otherwise returns false] AND self = #self
     * @return true if the player is at pos; otherwise, it returns false
     */
    default public boolean isPlayerAtPos(BoardPosition pos, char player) {
        // checking if the player is at the token's position
        if (whatsAtPos(pos) == player) {
            return true;
        } else {
            return false;
        }
    }

}
