/**
 * Solves the Non-Attacking Queens Problem. In order to solve this riddle, a set of Queens must be placed
 * on a blank chess board in a way that no queen is in the same column, row, or diagonal.
 * 
 * @Andrew Smith
 * @1/3/16
 */
public class QueensDriver
{
    public static void main (String[] args) {
        Board chessBoard = new Board(8,92); 
        chessBoard.solveBoard(0);
        System.out.print (chessBoard);
    }
}
