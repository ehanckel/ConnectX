package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE

arthorn765 - Abigail Thornton
grossmannjenna - Jenna Grossmann
ehanckel - Elle Hanckel

*/

/**
 * This is an abstract class for AbsGameBoard. It makes
 * the game board into a string to be printed out during game
 */

public abstract class AbsGameBoard implements IGameBoard {

    /**
     * returns a string representation of gameboard
     *
     * @pre none
     * @post toString() = [returns string representation of gameBoard] AND self = #self
     * @return one string that contains the entire board that will be easy to print
     * */
    @Override
    public String toString(){

        //creating string variable for gameboard
        String gameString = "|";
        for (int i = 0; i <= getNumColumns()-1; i++) {
            if (i < 10) {
                gameString += " " + i + "|";
            } else {
                gameString += i + "|";
            }
        }

        gameString += "\n";


        //nested for loop, looping through rows and columns
        for(int i = getNumRows() -1; i >= 0; i--) {
            for(int j = 0;j < getNumColumns(); j++) {
                //getting marker at position and adding to string
                BoardPosition pos = new BoardPosition(i,j);
                gameString += "|";
                gameString += whatsAtPos(pos) + " ";
            }
            gameString += ("|\n");
        }

        //returning gameboard as a string
        return gameString;
    }
}
