package Sudoku;


public class SudokuMaker {
    SudokuBoard board;

    public SudokuMaker() {
        board = new SudokuBoard();
        algorithem();
    }

    public SudokuMaker(int dimension) {
        board = new SudokuBoard(dimension);
        algorithem();
    }

    private void algorithem(){
        addRandomValues();
        try {
            fillBoard();
        } catch (Exception e) {
            board = new SudokuBoard(board.getDimension(), board.getQuadrantHeight(), board.getQuadrantWidth());
            algorithem();
        }
        

    }

    public SudokuMaker(int dimension, int quadrantHeight, int quadrantWidth) {
        this.board = new SudokuBoard(dimension, quadrantHeight, quadrantWidth);
    }

    public SudokuMaker(SudokuBoard board) {
        this.board = board;
    }

    public void addRandomValues(){
        addRandomValuesR(0, 0, 0);
    }

    private void addRandomValuesR(int x, int y, int count){
        int px = x;
        int py = y;
        for(int i = 0; i < board.getQuadrantWidth(); i++){
            int val = (int) (Math.random() * board.getDimension()) + 1;
            board.push(y, x, val);
            x += board.getQuadrantWidth();
            y++;
        }
        if(count < board.getQuadrantHeight() - 1)
            addRandomValuesR(px + 1, py + board.getQuadrantHeight(), count + 1);
    }

    private void fillBoard(){
        SudokuSolver solver = new SudokuSolver(board);
        board = solver.getSolvedBoard();
    }


    public SudokuBoard getBoard() {
        return board;
    }
}
