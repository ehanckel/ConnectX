package cpsc2150.extendedConnectX.models;


/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE

arthorn765 - Abigail Thornton
grossmannjenna - Jenna Grossmann
ehanckel - Elle Hanckel

*/

/**
 * This is a class for a BoardPosition object. The board has rows and columns in which
 * we can check if a location is empty or not. BoardPosition will be able to hold markers
 * in locations selected by users. We will be able to tell if there is a tie or if someone
 * won by looking at the diagonal, horizontal, and vertical markers.
 *
 * @invariant 0 <= Row < GameBoard.MAX_ROW AND 0 <= Column < GameBoard.MAX_COL
 */
public class BoardPosition
{
    private int Row;
    private int Column;


    /**
     * constructor for the BoardPostion object.
     *
     * @param aRow row an int that specifies y-axis location
     * @param aColumn column an int that specifies x-axis location
     * 
     * @pre 0 <= aRow < GameBoard.MAX_ROW AND 0 <= aColumn < GameBoard.MAX_COL
     * @post Row = aRow AND Column = aColumn
     * 
     */
    public BoardPosition(int aRow, int aColumn)
    {
        //parameterized constructor for BoardPosition
        Row = aRow;
        Column = aColumn;
    }

    /**
     * used to determine the position of the row
     *
     * @return returns the variable that indicates the position of the row
     * 
     * @pre none
     * @post getRow = Row AND Row = #Row AND Column = #Column
     */
    public int getRow()
    {
        //returns the row
        return Row;
    }

    /**
     * used to determine the position of the column
     *
     * @return returns the variable that indicates the position of the column
     * 
     * @pre none
     * @post getColumn = Column AND Row = #Row AND Column = #Column
     */
    public int getColumn()
    {
        //returns the column
        return Column;
    }

    /**
     * to see if one board position is equal to another board position
     *
     * @param obj an object containing valid row and column numbers
     * 
     * @pre none
     * @post equals = [true if obj.row && obj.column == existing row && column, otherwise false]
     * AND Row = #Row AND Column = #Column
     * 
     * @return true if they have same column amd row, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        // checking if object is of BoardPosition Type
        if (obj.getClass() == BoardPosition.class) {
           int objRow = ((BoardPosition) obj).getRow();
           int objCol = ((BoardPosition) obj).getColumn();

           // checking if the instance variables of BoardPosition are the same as the object's
           if (objRow == Row && objCol == Column) {
               return true;
           }
           else {
               return false;
           }
       }
        // not a BoardPosition Type
        else {
            return false;
        }
    };

    /**
     * used to return the location of a marker
     *
     * @pre none
     * @post toString() == “<row>,<column>” AND row = #row AND column = #column
     * @return the location of a marker will be returned in “row,column” format 
     */
    @Override
    public String toString()
    {
        String location;

        // getting row and column for string
        String row = Integer.toString(getRow());
        String col = Integer.toString(getColumn());

        // formatting row and column
        location = row + "," + col;

        return location;
    }
}