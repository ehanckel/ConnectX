package cpsc2150.extendedConnectX.models;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE

grossmannjenna- Jenna Grossmann
arthorn765 - Abby Thornton
ehanckel - Elle Hanckel

*/

/**
 * @corresponds self = hashMap<Character, List<BoardPosition>>
 * @invariant [pos below a placed token should not equal " "]
 */
public class GameBoardMem extends AbsGameBoard implements IGameBoard {

    private HashMap<Character, List<BoardPosition>> map;

    private int maxRow;

    private int maxCol;

    private int numToWin;

    /**
     * default constructor for the GameBoardMem object. Initializes the GameBoardMem
     * hashMap and variables
     * @param rows number of rows in the game board
     * @param cols number of columns in the game board
     * @param numWin number of tokens in a row to win in game board
     * @pre MIN_ROWS <= rows <= MAX_ROWS AND MIN_COLS <= cols <= MAX_COLS AND MIN_TOKENS <= numWin <= MAX_TOKENS
     * @post maxRow = rows AND maxCol = cols AND numToWin = numWin AND [Map is initialized]
     */
    public GameBoardMem(int rows, int cols, int numWin) {
        //GameBoardMem constructor initializing map
       maxRow = rows;
       maxCol = cols;
       numToWin = numWin;
       map = new HashMap<Character, List<BoardPosition>>();

    }

    /**
     * used to determine the number of rows in GameBoardMem
     *
     * @return returns the number of rows in GameBoardMem
     *
     * @pre none
     * @post getNumRow = maxRow
     */
    @Override
    public int getNumRows() {
        //returning number of rows
        return maxRow;
    }

    /**
     * used to determine the number of columns in GameBoardMem
     *
     * @return returns the number of columns in GameBoardMem
     *
     * @pre none
     * @post getNumColumns = maxCol
     */
    @Override
    public int getNumColumns() {
        //returning number of columns
        return maxCol;
    }

    /**
     * used to determine the number of tokens needed to win the game
     *
     * @return returns the number of tokens needed to win the game
     *
     * @pre none
     * @post getNumToWin = numToWin
     */
    @Override
    public int getNumToWin() {
        //returning number of tokens needed to win
        return numToWin;
    }

    /**
     * places a token in the next available spot in column c for the player p
     *
     * @param p a character that is used as a token
     * @param c an int that describes the column number
     * @pre checkIfFree(c) == true AND c >= 0 AND c < maxCol
     * @post [dropToken places the character p in BoardPosition pos (column c at the lowest available row) in the Map,
     * GameboardMem otherwise remains unchanged]
     * AND self = #self
     */
    @Override
    public void dropToken(char p, int c) {
        //check if column number is free
        if(checkIfFree(c)) {

            //start checking at bottom of board
            int row = 0;
            BoardPosition pos = new BoardPosition(row, c);

            //loop until next available row is found
            //while it's still occupied, create new position with next row up
            while (whatsAtPos(pos) != ' ') {
                row++;
                pos = new BoardPosition(row, c);
            }
            //if the player token already exists as a key in the map, add the new position to the list in the map
            if(map.containsKey(p)) {
                map.get(p).add(pos);
            }
            //if the player doesn't exist as a key, create a new map key-value pair and add the position to the list
            else {
                map.put(p,new ArrayList<BoardPosition>());
                map.get(p).add(pos);
            }
        }
    }

    /**
     * returns the player at specified position in map, if no player marker, returns a blank space char
     *
     * @pre none
     * @post whatsAtPos() = [returns the char that represents the player for the given position and a blank space if no player]
     * @post AND self = #self
     * @return  player p that has a token in given position or a blank space if empty
     */
    @Override
    public char whatsAtPos(BoardPosition pos) {
        //creating list variable
        List<BoardPosition> list = null;
        BoardPosition listPos;
        char p = ' ';

        //loop through each key
        for(Map.Entry<Character, List<BoardPosition>> entry :map.entrySet()) {
            list = entry.getValue();
            //loop through list
            for(int j = 0;j < list.size();j++) {
                listPos = list.get(j);
                //check if current board position equals specified position
                if(listPos.equals(pos)) {
                    //retrieve key to return specified player
                    p = entry.getKey();
                }
            }
        }
        //returning character at position
        return p;
    }
}