/**
 * Class that creates a board of given equal dimensions. Also has methods and variables pertaining to 
 * Queen chesspieces that can be placed on the board.
 * 
 * @Andrew Smith 
 * @2/14/16
 */
public class Board
{
    private int solvedBoardCount;
    private final int QUEEN = 1, EMPTY = 0;
    private int expectedSolutions;
    private int[][] chessBoard;
    private int[][][] solvedChessBoards; 
    //---------------------------------------------------------------------------------------------------------------------------------------------
    // Creates a chess Board object with given dimensions. Each Board is square, with equal sides.
    //---------------------------------------------------------------------------------------------------------------------------------------------
    public Board (int dimension, int solutions) {
        chessBoard = new int[dimension][dimension];
        expectedSolutions = solutions;
        solvedChessBoards = new int[expectedSolutions][dimension][dimension];
    }
    
    //---------------------------------------------------------------------------------------------------------------------------------------------
    // Creates the default chess Board object with dimensions of 4 on each side.
    //---------------------------------------------------------------------------------------------------------------------------------------------
    public Board () {
        chessBoard = new int[4][4];
        solvedChessBoards = new int[1][4][4];
    }
    
    //---------------------------------------------------------------------------------------------------------------------------------------------
    // This method does two things.
    // First, it solves the given chess board under the rules of the "Non-Attacking Queens" puzzle. It does this recursively, iterating through
    // all possible combinations of queen placement.
    //
    // Secondly, the method solves for every possible solution to the puzzle, and cataloges these solution 'boards' in the variable 
    // solvedChessBoards, a three dimensional array of 2 dimensional boards.
    //---------------------------------------------------------------------------------------------------------------------------------------------
    public void solveBoard(int row) {
        for (int column = 0; column < chessBoard.length; column++) { //Goes across the columns of a given row
            if (indexIsSafe(row, column)) { //Checks to see if the queen placement is valid
                chessBoard[row][column] = QUEEN; //Sets placevalue of 1
                if (row < chessBoard.length - 1) { //Unless the board has been solved
                    solveBoard(row+1);
                    deleteLastPosition(row);
                }else{
                    updateSolutions(solvedBoardCount, chessBoard); //Adds solved chessboard to the array
                    solvedBoardCount++; 
                    deleteLastPosition(row);
                } 
            }
        }
    }
    
    //---------------------------------------------------------------------------------------------------------------------------------------------
    // Takes a current row and column and tests to see if it is a viable spot for a queen.
    // This would mean no queens above or in a diagonal path.
    //---------------------------------------------------------------------------------------------------------------------------------------------
    private boolean indexIsSafe(int currentRow, int currentColumn) { //Tests the current index; Returns True if a queen can be placed
        int diagonal = 1; //Used to add/subtract in order to line up the diagonals of the chess board
        for (int testRow = currentRow - 1; testRow >= 0; testRow--) {
            if (chessBoard[testRow][currentColumn] == 1) { //Queen directly ABOVE
                return false;
            }
            //First checks to make sure there is an index to the left
            if (currentColumn - diagonal >= 0 && chessBoard[testRow][currentColumn - diagonal] == 1) { //Queen on LEFT DIAGONAL
                return false;
            }
            //First checks to make sure there is an index to the right
            if (currentColumn + diagonal < chessBoard.length && chessBoard[testRow][currentColumn + diagonal] == 1) { //Queen on RIGHT DIAGONAL
                return false;
            }
            diagonal += 1; //Adjusts for the increasing distance of diagonals
        }
        return true; //A queen can be placed at the current index
    }
    
    //---------------------------------------------------------------------------------------------------------------------------------------------
    // Deletes the last position (1 row above) in the given board. This is used to allow the program to progress along the columns without
    // containing past queen placements.
    //---------------------------------------------------------------------------------------------------------------------------------------------
    private void deleteLastPosition (int currentRow) {
        for (int column = 0; column < chessBoard.length; column++) { //Finds the queen in the row above the current row
                    if (chessBoard[currentRow][column] == 1) {
                        chessBoard[currentRow][column] = EMPTY;
                    }
        }
    }
    
    //---------------------------------------------------------------------------------------------------------------------------------------------
    // This method copies the values of the given chessBoard solution into the solvedChessBoards array
    //---------------------------------------------------------------------------------------------------------------------------------------------
    private void updateSolutions (int solutionIndex, int[][] newSolution) {
        for (int row = 0; row < newSolution.length; row++) {
            for (int column = 0; column < newSolution.length; column++) {
                solvedChessBoards[solutionIndex][row][column] = newSolution[row][column];
            }
        }
    }
    
    //---------------------------------------------------------------------------------------------------------------------------------------------
    // Prints the chess Board by returning a formatted string.
    // Empty spaces are represented by 0s, and queens by 1's.
    //---------------------------------------------------------------------------------------------------------------------------------------------
    public String toString () {
      String result = "";
      for (int[][] solvedBoard: solvedChessBoards) {
          for (int row = 0; row < solvedBoard.length; row++) {
              for (int column = 0; column < solvedBoard.length; column++) {
                  result += solvedBoard[row][column];
              }
              result += "\n";
          }
          result += "\n--------------------------------------------- \n";
      }
      result += "Expected Solutions: " + expectedSolutions + "\n";
      result += "Solutions Found: " + solvedBoardCount;
      return result;
    }
}
