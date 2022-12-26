package Sudoku;


import java.util.stream.IntStream;

public class SudokuSolver {
    private SudokuBoard board;
    private SudokuBoard solvedBoard;

    public SudokuSolver(SudokuBoard board) {
        this.board = board;
        this.solvedBoard = board.clone();
        
    }

    public SudokuBoard getSolvedBoard() {
        boolean solved = solve(this.solvedBoard.getBoard());
        if (!solved) {
            throw new IllegalArgumentException("Board is not solvable");
        }
        return this.solvedBoard;
    }

    private boolean solve(int[][] board) {
        for (int row = 0; row < this.board.getDimension(); row++) {
            for (int column = 0; column < this.board.getDimension(); column++) {
                if (board[row][column] == SudokuBoard.EMPTY) {
                    for (int k = 1; k <= this.board.getDimension(); k++) {
                        board[row][column] = k;
                        if (isValid(board, row, column) && solve(board)) {
                            return true;
                        }
                        board[row][column] = SudokuBoard.EMPTY;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int[][] board, int row, int column) {
    return (rowConstraint(board, row)
      && columnConstraint(board, column) 
      && subsectionConstraint(board, row, column));
}

    private boolean rowConstraint(int[][] board, int row) {
        boolean[] constraint = new boolean[this.board.getDimension()];
        return IntStream.range(0, this.board.getDimension())
                .allMatch(column -> checkConstraint(board, row, constraint, column));
    }

    private boolean columnConstraint(int[][] board, int column) {
        boolean[] constraint = new boolean[this.board.getDimension()];
        return IntStream.range(0, this.board.getDimension())
                .allMatch(row -> checkConstraint(board, row, constraint, column));
    }

    private boolean subsectionConstraint(int[][] board, int row, int column) {
        boolean[] constraint = new boolean[this.board.getDimension()];
        int boxRow = row - row % this.board.getQuadrantHeight();
        int boxColumn = column - column % this.board.getQuadrantWidth();

        for (int i = boxRow; i < boxRow + this.board.getQuadrantHeight(); i++) 
            for (int j = boxColumn; j < boxColumn + this.board.getQuadrantWidth(); j++) 
                if (!checkConstraint(board, i, constraint, j)) 
                    return false;
                
            
        
        return true;
    }

    boolean checkConstraint(int[][] board,int row,boolean[] constraint,int column) {
        if (board[row][column] != SudokuBoard.EMPTY) {
            if (!constraint[board[row][column] - 1]) 
                constraint[board[row][column] - 1] = true;
            else 
                return false;
        }
            
        return true;
    }

}