package cpsc2150.extendedConnectX.tests;

import cpsc2150.extendedConnectX.models.BoardPosition;
import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.IGameBoard;

import org.junit.Test;

// import java.util.Arrays;

import java.lang.reflect.Array;

import static org.junit.Assert.*;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE

arthorn765 - Abigail Thornton
grossmannjenna - Jenna Grossmann
ehanckel - Elle Hanckel

*/

public class TestGameBoard {

    private char[][] makeExpected(int row, int col) {
		char[][] expected = new char[row][col];
        for (int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                expected[i][j] = ' ';
            }
        }
        return expected;
    }

    private IGameBoard IGameFactory(int row, int column, int tokens) {
        return new GameBoard(row, column, tokens);
    }

    public String makeString(int row, int column, char[][] myArr) {
        String gameString = "|";
        for (int i = 0; i <= column-1; i++) {
            if (i < 10) {
                gameString += " " + i + "|";
            } else {
                gameString += i + "|";
            }
        }
        gameString += "\n";

        for(int i = row - 1; i >= 0; i--) {
            for(int j = 0; j < column; j++) {
                //getting marker at position and adding to string
                gameString += "|";
                //refer directly to array index (only printing empty board)
                gameString += myArr[i][j] + " ";
            }
            gameString += ("|\n");
        }

        //returning gameboard as a string
        return gameString;

    }


    // GameBoard Constructor Test #1
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#GameBoard(int userRow, int userCol, int token)} where we test
     *     the gameboard array initializes correctly and only has blank space characters.
     * </p>
     */
    @Test
    public void testGameBoardConstructor_empty_array_initialized() {
        // GameBoard and GameBoardMem constructor
        int row = 5;
        int column = 5;
        int tokens = 3;

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        String expectedString = makeString(row, column, expected);

        //put toString and creating 2D array into one function -> constructor and dropToken
        assertEquals(expectedString, gb.toString());
    }

    // GameBoard Constructor Test #2
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#GameBoard(int userRow, int userCol, int token)} where we test
     *     the gameboard array initializes correctly with the minimum amount of rows, columns, and # of tokens
     *     and only has blank space characters.
     * </p>
     */
    @Test
    public void testGameBoardConstructor_minimum_size() {
        // GameBoard and GameBoardMem constructor
        int row = 3;
        int column = 3;
        int tokens = 3;

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        String expectedString = makeString(row, column, expected);
        //put toString and creating 2D array into one function -> constructor and dropToken
        assertEquals(expectedString, gb.toString());
    }

    // GameBoard Constructor Test #3
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#GameBoard(int userRow, int userCol, int token)} where we test
     *     the gameboard array initializes correctly with maximum number of rows, columns, and # of tokens
     *     and only has blank space characters.
     * </p>
     */
    @Test
    public void testGameBoardConstructor_maximum_size() {
        // GameBoard and GameBoardMem constructor
        int row = 100;
        int column = 100;
        int tokens = 25;

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        String expectedString = makeString(row, column, expected);
        //put toString and creating 2D array into one function -> constructor and dropToken
        assertEquals(expectedString, gb.toString());
    }

    // checkIfFree Test #1
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#checkIfFree(int c)} where we
     *     check that the function can recognize that column 0 was full when checkIfFree was called.
     * </p>
     */
    @Test
    public void testCheckIfFree_full_far_left_column() {
        // variables used
        int row = 5;
        int column = 5;
        int tokens = 4;
        char player = 'A';
        int leftCol = 0;

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        //populated left most column with markers with no empty space open
        for (int i = 0; i < row; i++) {
            gb.dropToken(player, leftCol);
            expected[i][leftCol] = player;
            player++;
        }

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        //checking if checkIfFree works correctly
        assertTrue(!gb.checkIfFree(leftCol));
    }

    // checkIfFree Test #2
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#checkIfFree(int c)} where we
     *     check that the function can recognize that column 4 was full when checkIfFree was called.
     * </p>
     */
    @Test
    public void testCheckIfFree_almost_filled_far_right_column() {

        int row = 5;
        int column = 5;
        int tokens = 5;
        char player = 'A';
        int rightCol = 4;

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        for (int i = 0; i < row - 1; i++) {
            gb.dropToken(player, rightCol);
            expected[i][rightCol] = player;
            player++;
        }

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        //checking if checkIfFree works correctly
        assertTrue(gb.checkIfFree(rightCol));

    }

    // checkIfFree Test #3
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#checkIfFree(int c)} where we
     *     check if the function can place a token in a completely empty column
     * </p>
     */
    @Test
    public void testCheckIfFree_completely_empty_column() {

        int row = 5;
        int column = 5;
        int tokens = 4;
        char player = 'X';
        int colToken = 0;
        int bottomRow = 0;
        int colCheck = 2;

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        gb.dropToken(player, colToken);
        expected[bottomRow][colToken] = player;

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        //checking if checkIfFree works correctly
        assertTrue(gb.checkIfFree(colCheck));
    }

    //CheckHorizWin #1
    /**
     * <p>
     *     This is a test case for testing {@link IGameBoard#checkHorizWin(BoardPosition, char)} where we
     *     check the function can recognize that there is a horizontal win when the last marker was placed between tokens
     * </p>
     */
    @Test
    public void testCheckHorizWin_win_last_marker_in_between_tokens() {
        int row = 5;
        int column = 5;
        int tokens = 5;
        char player1 = 'X';
        char player2 = 'O';

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //creating expected
        char[][] expected = makeExpected(row, column);

        //setting up the boards
        for(int i = 0;i < 2;i++) {
            for(int j = 0;j < column;j++) {
                //fill in the Os
                if(i == 0 && j != 3) {
                    expected[i][j] = player2;
                    gb.dropToken(player2, j);
                }
                else {  //fill in the Xs
                    expected[i][j] = player1;
                    gb.dropToken(player1, j);
                    if(j == 4)  //don't place the last X in the 2nd row
                        break;
                }
            }
        }

        //create board position to test
        BoardPosition iterPosition = new BoardPosition(1,2);

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        assertTrue(gb.checkHorizWin(iterPosition,player1));
    }

    //CheckHorizWin #2
    /**
     * <p>
     *     This is a test case for testing {@link IGameBoard#checkHorizWin(BoardPosition, char)} where we
     *     check the function can recognize that there is a horizontal win when the last marker was placed
     *     on the left side in the bottom row
     * </p>
     */
    @Test
    public void testCheckHorizWin_win_last_marker_left_bottom_row() {
        int row = 5;
        int column = 5;
        int tokens = 4;
        char player1 = 'X';
        char player2 = 'O';

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //creating expected
        char[][] expected = makeExpected(row, column);

        //setting up the boards
        for(int i = 0;i < column - 1; i++) {
            expected[0][i] = player1;
            gb.dropToken(player1, i);
        }

        expected[0][4] = player2;
        gb.dropToken(player2, 4);

        //create board position to test
        BoardPosition iterPosition = new BoardPosition(0,0);

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        assertTrue(gb.checkHorizWin(iterPosition,player1));
    }

    //CheckHorizWin #3
    /**
     * <p>
     *     This is a test case for testing {@link IGameBoard#checkHorizWin(BoardPosition, char)} where we
     *     check the function can recognize that there is a horizontal win when the last marker was placed
     *     on the right side in the top row
     * </p>
     */
    @Test
    public void testCheckHorizWin_win_last_marker_right_top_row() {
        int row = 5;
        int column = 5;
        int tokens = 5;
        char player1 = 'X';
        char player2 = 'O';

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //creating expected
        char[][] expected = makeExpected(row, column);

        //setting up the boards
        for(int i = 0;i < row; i++) {
           for(int j = 0;j < column;j++) {
               if((j != 2 && i < 3) || (j == 2 && i == 3)) {
                   expected[i][j] = player2;
                   gb.dropToken(player2, j);
               }
               else {
                   expected[i][j] = player1;
                   gb.dropToken(player1,j);
               }
           }
        }

        //create board position to test
        BoardPosition iterPosition = new BoardPosition(4,4);

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        assertTrue(gb.checkHorizWin(iterPosition,player1));
    }

    //CheckHorizWin #4
    /**
     * <p>
     *     This is a test case for testing {@link IGameBoard#checkHorizWin(BoardPosition, char)} where we
     *     check the function can recognize that there is not a horizontal win
     * </p>
     */
    @Test
    public void testCheckHorizWin_no_win_() {
        int row = 5;
        int column = 5;
        int tokens = 4;
        char player1 = 'X';
        char player2 = 'O';

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //creating expected
        char[][] expected = makeExpected(row, column);

        //setting up the boards
        for(int i = 0;i < 2; i++) {
            for(int j = 0;j < 2;j++) {
                expected[i][j] = player1;
                gb.dropToken(player1, j);
            }
        }

        expected[0][2] = player2;
        gb.dropToken(player2, 2);

        expected[1][2] = player2;
        gb.dropToken(player2, 2);

        //create board position to test
        BoardPosition iterPosition = new BoardPosition(1,1);

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        assertFalse(gb.checkHorizWin(iterPosition,player1));
    }

    //checkVertWin #1
    /**
     * <p>
     *     This is a test case for testing {@link IGameBoard#checkVertWin(BoardPosition, char)} where we
     *     check the function can recognize that there is not a vertical win
     * </p>
     */
    @Test
    public void testCheckVertWin_no_win() {
        int row = 5;
        int column = 5;
        int tokens = 4;
        char player1 = 'X';
        char player2 = 'O';

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //creating expected
        char[][] expected = makeExpected(row, column);

        //setting up the boards
        for(int i = 0;i < 3;i++) {
            expected[i][1] = player1;
            gb.dropToken(player1, 1);
        }

        expected[0][0] = player2;
        gb.dropToken(player2, 0);

        //create board position to test
        BoardPosition iterPosition = new BoardPosition(2,1);

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        assertFalse(gb.checkVertWin(iterPosition,player1));
    }

    //checkVertWin #2
    /**
     * <p>
     *     This is a test case for testing {@link IGameBoard#checkVertWin(BoardPosition, char)} where we
     *     check the function can recognize that there is a vertical win when the last marker was placed
     *     in the first column.
     * </p>
     */
    @Test
    public void testCheckVertWin_first_column_win() {
        int row = 5;
        int column = 5;
        int tokens = 3;
        char player1 = 'X';
        char player2 = 'O';


        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //creating expected
        char[][] expected = makeExpected(row, column);

        //setting up the boards
        for(int i = 0;i < 3;i++) {
            expected[i][0] = player1;
            gb.dropToken(player1, 0);
        }

        expected[0][4] = player2;
        gb.dropToken(player2, 4);

        //create board position to test
        BoardPosition iterPosition = new BoardPosition(2,0);

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        assertTrue(gb.checkVertWin(iterPosition,player1));
    }

    //checkVertWin #3
    /**
     * <p>
     *     This is a test case for testing {@link IGameBoard#checkVertWin(BoardPosition, char)} where we
     *     check the function can recognize that there is a vertical win when the last marker was placed
     *     in the last row and last column.
     * </p>
     */
    @Test
    public void testCheckVertWin_last_row_last_column_win() {
        int row = 5;
        int column = 5;
        int tokens = 4;
        char player1 = 'X';
        char player2 = 'O';


        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //creating expected
        char[][] expected = makeExpected(row, column);

        //setting up the boards
        expected[0][4] = player2;
        gb.dropToken(player2,4);

        for(int i = 1;i < 5;i++) {
            expected[i][4] = player1;
            gb.dropToken(player1, 4);
        }
        //create board position to test
        BoardPosition iterPosition = new BoardPosition(4,4);

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        assertTrue(gb.checkVertWin(iterPosition,player1));
    }

    //checkVertWin #4
    /**
     * <p>
     *     This is a test case for testing {@link IGameBoard#checkVertWin(BoardPosition, char)} where we
     *     check the function can recognize that there is a vertical win when the last marker was placed
     *     in the middle of the board.
     * </p>
     */
    @Test
    public void testCheckVertWin_middle_board_win() {
        int row = 5;
        int column = 5;
        int tokens = 4;
        char player2 = 'O';
        char player1 = 'X';


        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //creating expected
        char[][] expected = makeExpected(row, column);

        //setting up the boards
        for(int i = 0;i < 4;i++) {
            expected[i][2] = player2;
            gb.dropToken(player2, 2);
        }

        expected[0][1] = player1;
        gb.dropToken(player1, 1);

        //create board position to test
        BoardPosition iterPosition = new BoardPosition(3,2);

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
         assertEquals(expectedString, gb.toString());

         assertTrue(gb.checkVertWin(iterPosition,player2));
    }

    //checkDiagWin Test #1
    /**
     * <p>
     *     This is a test case for testing {@link IGameBoard#checkDiagWin(BoardPosition, char)} where we
     *     check the function can recognize that there is not a diagonal win present
     * </p>
     */
    @Test
    public void testCheckDiagWin_no_win() {
        int row = 5;
        int column = 5;
        int tokens = 3;
        char player1 = 'X';
        char player2 = 'O';


        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //creating expected
        char[][] expected = makeExpected(row, column);

        //setting up the boards
        for(int i = 0;i < 2;i++) {
            for(int j = 1;j < 3;j++) {
                expected[i][j] = player1;
                gb.dropToken(player1, j);
            }
        }

        expected[0][0] = player2;
        gb.dropToken(player2, 0);

        expected[1][0] = player2;
        gb.dropToken(player2, 0);

        //create board position to test
        BoardPosition iterPosition = new BoardPosition(1,2);

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        assertFalse(gb.checkDiagWin(iterPosition,player1));
    }

    //checkDiagWin Test #2
    /**
     * <p>
     *     This is a test case for testing {@link IGameBoard#checkDiagWin(BoardPosition, char)} where we
     *     check the function can recognize that there is a win in the top right position of a bottom left to
     *     top right diagonal. It also tests that it works on the top row and last column on the board.
     * </p>
     */
    @Test
    public void testCheckDiagWin_top_pos_right_diag_win_last_row_last_col() {
        int row = 5;
        int column = 5;
        int tokens = 5;
        char player1 = 'X';
        char player2 = 'O';

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //creating expected
        char[][] expected = makeExpected(row, column);

        //setting up the boards

        for(int i = 0;i < 5;i++) {
            for(int j = i;j < 4;j++) {
                expected[i][j] = player1;
                gb.dropToken(player1, j);
            }
        }
        for(int k = 0;k < 5;k++) {
            if(k != 4) {
                expected[k][4] = player2;
                gb.dropToken(player2, 4);
            }
            else {
                expected[k][4] = player1;
                gb.dropToken(player1,4);
            }
        }
        //create board position to test
        BoardPosition iterPosition = new BoardPosition(4,4);

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        assertTrue(gb.checkDiagWin(iterPosition,player1));
    }

    //checkDiagWin Test #3
    /**
     * <p>
     *     This is a test case for testing {@link IGameBoard#checkDiagWin(BoardPosition, char)} where we
     *     check the function can recognize that there is a win in the bottom right position of a bottom right
     *     to top left diagonal. It also tests that it works correctly on the bottom row of the board.
     * </p>
     */
    @Test
    public void testCheckDiagWin_bottom_pos_left_diag_win() {
        int row = 5;
        int column = 5;
        int tokens = 3;
        char player1 = 'X';
        char player2 = 'O';

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //creating expected
        char[][] expected = makeExpected(row, column);

        //setting up the boards

        for(int i = 0;i < 3;i++) {
            for(int j = 0;j < 3;j++) {
                if(i == 0 && j == 0) {
                    expected[i][j] = player2;
                    gb.dropToken(player2, j);
                }
                else {
                    if ((i == 0 && (j == 1 || j == 2)) || (i == 1 &&
                            (j == 0 || j == 1)) || (i == 2 && j == 0)) {
                        expected[i][j] = player1;
                        gb.dropToken(player1, j);
                    }
                }
            }
        }


        //create board position to test
        BoardPosition iterPosition = new BoardPosition(0,2);

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        assertTrue(gb.checkDiagWin(iterPosition,player1));
    }

    //checkDiagWin Test #4
    /**
     * <p>
     *     This is a test case for testing {@link IGameBoard#checkDiagWin(BoardPosition, char)} where we
     *     check the function can recognize that there is a diagonal win in the middle position of a right diagonal
     *     in the middle of the board.
     * </p>
     */
    @Test
    public void testCheckDiagWin_middle_pos_right_diag_win() {
        int row = 5;
        int column = 5;
        int tokens = 3;
        char player1 = 'X';
        char player2 = 'O';

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //creating expected
        char[][] expected = makeExpected(row, column);

        //setting up the boards
        for(int i = 0;i < 4;i++) {
            for(int j = i;j < 4;j++) {
                if(j != 0) {
                    if((i == 1 && (j == 2 || j == 3)) || (i == 0 && j == 2)){
                        expected[i][j] = player2;
                        gb.dropToken(player2,j);
                    }
                    else {
                        expected[i][j] = player1;
                        gb.dropToken(player1,j);
                    }
                }
            }
        }


        //create board position to test
        BoardPosition iterPosition = new BoardPosition(2,2);

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        assertTrue(gb.checkDiagWin(iterPosition,player1));
    }

    //checkDiagWin Test #5
    /**
     * <p>
     *     This is a test case for testing {@link IGameBoard#checkDiagWin(BoardPosition, char)} where we
     *     check the function can recognize that there is a win in the top position of a left diagonal in the
     *     middle of the board.
     * </p>
     */
    @Test
    public void testCheckDiagWin_top_pos_left_diag_win() {
        int row = 5;
        int column = 5;
        int tokens = 3;
        char player1 = 'X';
        char player2 = 'O';

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //creating expected
        char[][] expected = makeExpected(row, column);

        //setting up the boards

        for(int i = 0;i < 4;i++) {
            for(int j = 1;j < 4;j++) {
                if((i == 0 && j != 1) || (i == 1 && j == 3) ||
                        (i == 2 && j != 3) || (i == 3 && j == 1)){
                    expected[i][j] = player1;
                    gb.dropToken(player1,j);
                }
                else if((i == 0 && j == 1) || (i == 1 & j != 3)) {
                    expected[i][j] = player2;
                    gb.dropToken(player2,j);
                }
            }
        }

        //create board position to test
        BoardPosition iterPosition = new BoardPosition(3,1);

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        assertTrue(gb.checkDiagWin(iterPosition,player1));
    }

    //checkDiagWin Test #6
    /**
     * <p>
     *     This is a test case for testing {@link IGameBoard#checkDiagWin(BoardPosition, char)} where we
     *     check the function can recognize that there is a win in the bottom position of a left diagonal
     *     in the middle of the board.
     * </p>
     */
    @Test
    public void testCheckDiagWin_bottom_pos_right_diag_win() {
        int row = 5;
        int column = 5;
        int tokens = 3;
        char player1 = 'X';
        char player2 = 'O';

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //creating expected
        char[][] expected = makeExpected(row, column);

        //setting up the boards
        for(int i = 0;i < 4;i++) {
            for(int j = 1;j < 4;j++) {
                if((i == 0 && j != 3) || (i == 1 && (j == 2 || j == 3)) ||
                        (i == 2 && j == 3)) {
                    expected[i][j] = player1;
                    gb.dropToken(player1,j);
                }
                else if(i == 0 && j == 3) {
                    expected[i][j] = player2;
                    gb.dropToken(player2,j);
                }
            }
        }

        //create board position to test
        BoardPosition iterPosition = new BoardPosition(0,1);

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        assertTrue(gb.checkDiagWin(iterPosition,player1));
    }

    //checkDiagWin Test #7
    /**
     * <p>
     *     This is a test case for testing {@link IGameBoard#checkDiagWin(BoardPosition, char)} where we
     *     check the function can recognize that there is a win in the middle position of 2 diagonal wins.
     * </p>
     */
    @Test
    public void testCheckDiagWin_middle_pos_two_diags_win() {
        int row = 5;
        int column = 5;
        int tokens = 3;
        char player1 = 'X';
        char player2 = 'O';

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //creating expected
        char[][] expected = makeExpected(row, column);

        //setting up the boards
        //first column
        for(int i = 0;i < 4;i++) {
            if(i == 2) {
                expected[i][0] = player2;
                gb.dropToken(player2,0);
            }
            else {
                expected[i][0] = player1;
                gb.dropToken(player1,0);
            }
        }
        //second column
        for(int i = 0;i < 4;i++) {
            if(i == 2) {
                expected[i][1] = player1;
                gb.dropToken(player1,1);
            }
            else {
                expected[i][1] = player2;
                gb.dropToken(player2,1);
            }
        }
        //third column
        for(int i = 0;i < 4;i++) {
            if(i == 2) {
                expected[i][2] = player2;
                gb.dropToken(player2,2);
            }
            else {
                expected[i][2] = player1;
                gb.dropToken(player1,2);
            }
        }

        //create board position to test
        BoardPosition iterPosition = new BoardPosition(2,1);

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        assertTrue(gb.checkDiagWin(iterPosition,player1));
    }

    // checkTie Test #1
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#checkTie()} where we
     *     check that function can recognize when the gameboard is partially full.
     * </p>
     */
    @Test
    public void testCheckTie_board_not_full() {
        int row = 5;
        int column = 5;
        int tokens = 5;
        char player = 'X';
        char playerTwo = 'O';
        int rightCol = 4;

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        for (int i = 0; i < row - 1; i++) {
            gb.dropToken(player, rightCol);
            expected[i][rightCol] = player;
        }
        for (int j = 0; j < 2; j++) {
            gb.dropToken(playerTwo, j);
            expected[0][j] = playerTwo;
        }

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        //checking if checkTie works correctly (it returns false)
        assertTrue(!gb.checkTie());

    }

    // checkTie Test #2
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#checkTie()} where we
     *     check that function can recognize a tie when the board is full.
     * </p>
     */
    @Test
    public void testCheckTie_board_full_no_win() {
        int row = 5;
        int column = 5;
        int tokens = 5;
        char playerOne = 'X';
        char playerTwo = 'O';
        int XOcol = 0;
        char token = 'A';

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                gb.dropToken(token, j);
                expected[i][j] = token;
                token++;
            }
        }

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        //checking if checkTie works correctly (return true)
        assertTrue(gb.checkTie());

    }

    // checkTie Test #3
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#checkTie()} where we
     *     check that function can recognize when the gameboard is empty.
     * </p>
     */
    @Test
    public void testCheckTie_board_empty() {
        int row = 5;
        int column = 5;
        int tokens = 5;

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        //checking if checkTie works correctly
        assertTrue(!gb.checkTie());

    }

    // checkTie Test #4
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#checkTie()} where we
     *     check that function can recognize that a full board has a win and not a tie.
     * </p>
     */
    @Test
    public void testCheckTie_board_full_win_no_tie() {
        int row = 5;
        int column = 5;
        int tokens = 5;
        char playerOne = 'X';
        char playerTwo = 'O';
        int XOcol = 0;
        int lastCol = 4;

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        for (int i = 0; i < column - 1; i++) {
            if (XOcol % 2 == 0) {
                for (int j = 0; j < row - 1; j++) {
                    gb.dropToken(playerOne, i);
                    expected[j][i] = playerOne;
                }
                // top row
                gb.dropToken(playerTwo, i);
                expected[row - 1][i] = playerTwo;
                XOcol++;
            }
            if (XOcol % 2 == 1) {
                for (int j = 0; j < row - 1; j++) {
                    gb.dropToken(playerOne, i);
                    expected[j][i] = playerOne;
                }
                // top row
                gb.dropToken(playerTwo, i);
                expected[row - 1][i] = playerTwo;
                XOcol++;

            }
        }
        for (int k = 0; k < row; k++) {
            gb.dropToken(playerTwo, lastCol);
            expected[k][lastCol] = playerTwo;
        }

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        //checking if checkTie works correctly
        assertTrue(!gb.checkTie());

    }


    //whatsAtPos test #1
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#whatsAtPos(BoardPosition)} where we check that the function
     *     returns the correct character in an empty position on the board
     * </p>
     */
    @Test
    public void testWhatsAtPos_empty_pos() {

        int row = 5;
        int column = 5;
        int tokens = 4;
        char player = 'X';
        char playerTwo = 'O';
        int testRow = 0;
        int testCol = 3;
        char empty = ' ';

        BoardPosition pos = new BoardPosition(testRow, testCol);

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        for (int i = 0; i < testCol; i++) {
            gb.dropToken(player, i);
            expected[testRow][i] = player;
        }
        for (int j = 0; j < 2; j++) {
            gb.dropToken(playerTwo, 4);
            expected[j][4] = playerTwo;
        }

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        //checking if whatsAtPos works correctly
        assertEquals(empty, gb.whatsAtPos(pos));

    }

    //whatsAtPos Test #2
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#whatsAtPos(BoardPosition)} where we check that the function
     *     returns the correct character in the bottom row of a column on the board
     * </p>
     */
    @Test
    public void testWhatsAtPos_bottom_row_pos() {

        int row = 5;
        int column = 5;
        int tokens = 4;
        char player = 'X';
        char playerTwo = '0';
        int testRow = 0;
        int testCol = 2;

        BoardPosition pos = new BoardPosition(testRow, testCol);

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        for (int i = column - 1; i >= testCol; i--) {
            gb.dropToken(player, i);
            expected[testRow][i] = player;
        }
        for (int j = 0; j < 2; j++) {
            gb.dropToken(playerTwo, 0);
            expected[j][0] = playerTwo;
        }

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        assertEquals(player, gb.whatsAtPos(pos));

    }

    //whatsAtPos Test #3
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#whatsAtPos(BoardPosition)} where we check that the function
     *     returns the correct character in the top row of a column on the board
     * </p>
     */
    @Test
    public void testWhatsAtPos_top_row_pos() {
        int row = 5;
        int column = 5;
        int tokens = 4;
        int testCol = 1;
        int testRow = 4;
        char player = 'A';
        char lastPos = 'E';

        BoardPosition pos = new BoardPosition(testRow, testCol);

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        for (int i = 0; i < row; i++) {
            gb.dropToken(player, testCol);
            expected[i][testCol] = player;
            player++;
        }

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        assertEquals(lastPos, gb.whatsAtPos(pos));


    }

    //whatsAtPos Test #4
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#whatsAtPos(BoardPosition)} where we check that the function
     *     returns the correct character in the first row of the first column on the board
     * </p>
     */
    @Test
    public void testWhatsAtPos_first_column_pos() {
        int row = 5;
        int column = 5;
        int tokens = 4;
        int testCol = 0;
        int testRow = 0;
        char player = 'X';

        BoardPosition pos = new BoardPosition(testRow, testCol);

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        gb.dropToken(player, testCol);
        expected[testRow][testCol] = player;

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        assertEquals(player, gb.whatsAtPos(pos));

    }

    //whatsAtPos Test #5
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#whatsAtPos(BoardPosition)} where we check that the function
     *     returns the correct character in the first row of the last column on the board
     * </p>
     */
    @Test
    public void testWhatsAtPos_last_column_pos() {
        int row = 5;
        int column = 5;
        int tokens = 4;
        int testCol = 4;
        int testRow = 0;
        char player = 'X';

        BoardPosition pos = new BoardPosition(testRow, testCol);

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        gb.dropToken(player, testCol);
        expected[testRow][testCol] = player;

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        assertEquals(player, gb.whatsAtPos(pos));
    }


    // isPlayerAtPos Test #1
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#isPlayerAtPos(BoardPosition pos, char player)} where we
     *     check that the function recognizes that there is no player at the position on the board being looked at
     * </p>
     */
    @Test
    public void testIsPlayerAtPos_empty_position() {

        int row = 5;
        int column = 5;
        int tokens = 4;
        char player = 'X';
        char playerTwo = '0';
        int testRow = 0;
        int testCol = 3;

        BoardPosition pos = new BoardPosition(testRow, testCol);

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        for (int i = 0; i < testCol; i++) {
            gb.dropToken(player, i);
            expected[testRow][i] = player;
        }
        for (int j = 0; j < 2; j++) {
            gb.dropToken(playerTwo, 4);
            expected[j][4] = playerTwo;
        }

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        //checking if checkIfFree works correctly
        assertTrue(!gb.isPlayerAtPos(pos, player));


    }

    // isPlayerAtPos Test #2
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#isPlayerAtPos(BoardPosition pos, char player)} where we
     *     check that the function recognizes that the correct player is at the position on the board being looked at
     * </p>
     */
    @Test
    public void testIsPlayerAtPos_correct_player() {

        int row = 5;
        int column = 5;
        int tokens = 4;
        char player = 'A';
        char expPlayer = 'B';
        int testRow = 0;
        int testCol = 1;

        BoardPosition pos = new BoardPosition(testRow, testCol);

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        for (int i = 0; i < column; i++) {
            gb.dropToken(player, i);
            expected[testRow][i] = player;
            player++;
        }

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        //checking if checkIfFree works correctly
        assertTrue(gb.isPlayerAtPos(pos, expPlayer));

    }

    // isPlayerAtPos Test #3
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#isPlayerAtPos(BoardPosition pos, char player)} where we
     *     check that the function recognizes that an incorrect player is at the position on the board being looked at
     * </p>
     */
    @Test
    public void testIsPlayerAtPos_incorrect_player() {

        int row = 5;
        int column = 5;
        int tokens = 4;
        char player1 = 'X';
        char player2 = 'O';
        int testRow = 2;
        int testCol = 2;
        int max = 3;

        BoardPosition pos = new BoardPosition(testRow, testCol);

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        for (int i = 0; i < max; i++) {
            if (i % 2 == 0) {
                gb.dropToken(player1, testCol);
                expected[i][testCol] = player1;
            } else {
                gb.dropToken(player2, testCol);
                expected[i][testCol] = player2;
            }
        }

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        //checking if checkIfFree works correctly
        assertTrue(!gb.isPlayerAtPos(pos, player2));

    }

    // isPlayerAtPos Test #4
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#isPlayerAtPos(BoardPosition pos, char player)} where we
     *     check the function can recognize that the player it is looking for is at the bottom row of the first column.
     * </p>
     */
    @Test
    public void testIsPlayerAtPos_first_column_bottom_row() {

        int row = 5;
        int column = 5;
        int tokens = 4;
        char player1 = 'X';
        char player2 = 'O';
        int testRow = 0;
        int testCol = 0;

        BoardPosition pos = new BoardPosition(testRow, testCol);

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        for (int i = 0; i < column; i++) {
            if (i % 2 == 0) {
                gb.dropToken(player1, i);
                expected[testRow][i] = player1;
            } else {
                gb.dropToken(player2, i);
                expected[testRow][i] = player2;
            }
        }

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        //checking if isPlayerAtPos works correctly
        assertTrue(gb.isPlayerAtPos(pos, player1));
    }

    // isPlayerAtPos Test #5
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#isPlayerAtPos(BoardPosition pos, char player)} where we
     *     check the function can recognize that the player it is looking for is at the top row of the last column.
     * </p>
     */
    @Test
    public void testIsPlayerAtPos_top_row_last_column() {

        int row = 5;
        int column = 5;
        int tokens = 4;
        char player1 = 'X';
        char player2 = 'O';
        int testRow = 4;
        int testCol = 4;

        BoardPosition pos = new BoardPosition(testRow, testCol);

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        for (int i = 0; i < row; i++) {
            if (i % 2 == 0) {
                gb.dropToken(player1, testCol);
                expected[i][testCol] = player1;
            } else {
                gb.dropToken(player2, testCol);
                expected[i][testCol] = player2;
            }
        }

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

        //checking if isPlayerAtPos works correctly
        assertTrue(gb.isPlayerAtPos(pos, player1));
        
    }

    // dropToken Test #1
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#dropToken(char p, int c)} where we
     *     check that a token gets dropped in the first available row of the first column on the board.
     * </p>
     */
    @Test
    public void testDropToken_first_column() {
        int row = 5;
        int column = 5;
        int tokens = 4;
        char player = 'X';

        int testRow = 0;
        int testCol = 0;

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        gb.dropToken(player, testCol);
        expected[testRow][testCol] = player;

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

    }

    // dropToken Test #2
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#dropToken(char p, int c)} where we
     *     check that a token gets dropped in the first available row in the last column on the board.
     * </p>
     */
    @Test
    public void testDropToken_last_column() {

        int row = 5;
        int column = 5;
        int tokens = 4;
        char player = 'X';

        int testRow = 0;
        int testCol = 4;

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        gb.dropToken(player, testCol);
        expected[testRow][testCol] = player;

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());
    }

    // dropToken Test #3
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#dropToken(char p, int c)} where we
     *     check that a token gets dropped in the middle of the board.
     * </p>
     */
    @Test
    public void testDropToken_middle_pos() {
        int row = 5;
        int column = 5;
        int tokens = 4;
        char player = 'X';
        char playerTwo = '0';
        int testRow = 2;
        int testCol = 2;

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        for (int i = 0; i <= testRow; i++) {
            gb.dropToken(player, testCol);
            expected[i][testCol] = player;
        }
        for (int j = 0; j < 2; j++) {
            gb.dropToken(playerTwo, 4);
            expected[j][4] = playerTwo;
        }

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());
    }

    // dropToken Test #4
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#dropToken(char p, int c)} where we
     *     check that a token does not replace the last token in an already full column.
     * </p>
     */
    @Test
    public void testDropToken_full_column() {
        int row = 5;
        int column = 5;
        int tokens = 4;
        char player1 = 'X';
        char player2 = 'O';

        int testCol = 2;

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        for (int i = 0; i < row; i++) {
            if (i % 2 == 0) {
                gb.dropToken(player1, testCol);
                expected[i][testCol] = player1;
            } else {
                gb.dropToken(player2, testCol);
                expected[i][testCol] = player2;
            }

        }

        //should NOT replace top character with an O
        gb.dropToken(player2, testCol);

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());
    }

    // dropToken Test #5
    /**
     * <p>
     *     This is a test case for testing {@link GameBoard#dropToken(char p, int c)} where we
     *     check that a token gets dropped in the last available position on the board.
     * </p>
     */
    @Test
    public void testDropToken_last_pos() {

        int row = 5;
        int column = 5;
        int tokens = 5;
        char player = 'A';
        char lastPos = 'Y';
        int lastRow = 4;
        int lastCol = 4;

        //creating observed
        IGameBoard gb = IGameFactory(row, column, tokens);

        //created expected
        char[][] expected = makeExpected(row, column);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column - 1; j++) {
                gb.dropToken(player, j);
                expected[i][j] = player;
                player++;
            }
        }

        for (int i = 0; i < row - 1; i++) {
            gb.dropToken(player, lastCol);
            expected[i][lastCol] = player;
            player++;
        }

        //should NOT replace top character with an O
        gb.dropToken(lastPos, lastCol);
        expected[lastRow][lastCol] = lastPos;

        String expectedString = makeString(row, column, expected);

        //checking the expected and gb are equal
        assertEquals(expectedString, gb.toString());

    }
}
