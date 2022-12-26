import Sudoku.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        SudokuMaker maker = new SudokuMaker();
        SudokuBoard board = maker.getBoard();
        



        System.out.println(board);
        
    }
}
