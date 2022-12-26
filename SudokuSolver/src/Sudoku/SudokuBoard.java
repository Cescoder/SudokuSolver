package Sudoku;

public class SudokuBoard {
    private static int DEFAULT_DIMENSION = 9;
    private static int DEFAULT_QUADRANT_HEIGTH = 3;
    private static int DEFAULT_QUADRANT_WIDTH = 3;

    public static int EMPTY = 0;

    private int dimension;
    private int quadrantHeight;
    private int quadrantWidth;
    private int[][] board;

    public SudokuBoard() {
        setSudokuParameter(DEFAULT_DIMENSION, DEFAULT_QUADRANT_HEIGTH, DEFAULT_QUADRANT_WIDTH);
    }

    public SudokuBoard(int dimension) {
        int quadrantHeight = (int) Math.sqrt(dimension);
        int quadrantWidth = (int) Math.sqrt(dimension);

        if (quadrantHeight * quadrantWidth != dimension)
            throw new IllegalArgumentException("Dimension must be a perfect square");

        setSudokuParameter(dimension, quadrantHeight, quadrantWidth);
    }

    public SudokuBoard(int dimension, int quadrantHeight, int quadrantWidth) {
        setSudokuParameter(dimension, quadrantHeight, quadrantWidth);
    }

    public int getDimension() {
        return dimension;
    }

    public int getQuadrantHeight() {
        return quadrantHeight;
    }

    public int getQuadrantWidth() {
        return quadrantWidth;
    }

    public void setDimension(int dimension) {
        setSudokuParameter(dimension, getQuadrantHeight(), getQuadrantWidth());
    }

    public void setQuadrantHeight(int quadrantHeight) {
        setSudokuParameter(getDimension(), quadrantHeight, getQuadrantWidth());
    }

    public void setQuadrantWidth(int quadrantWidth) {
        setSudokuParameter(getDimension(), getQuadrantHeight(), quadrantWidth);
    }

    public void setSudokuParameter(int dimension, int quadrantHeight, int quadrantWidth) {
        if (dimension < 1)
            throw new IllegalArgumentException("Dimension must be greater than 0");

        if (quadrantHeight < 1)
            throw new IllegalArgumentException("Quadrant height must be greater than 0");

        if (quadrantWidth < 1)
            throw new IllegalArgumentException("Quadrant width must be greater than 0");

        if (dimension % quadrantHeight != 0 || dimension % quadrantWidth != 0)
            throw new IllegalArgumentException("Dimension must be a multiple of quadrant height and width");

        if (quadrantHeight * quadrantWidth != dimension)
            throw new IllegalArgumentException("The area of the quadrant must be equal to the dimension");

        this.dimension = dimension;
        this.quadrantHeight = quadrantHeight;
        this.quadrantWidth = quadrantWidth;

        board = new int[dimension][dimension];
    }

    public void push(int row, int column, int value){
        if(value > dimension || value < 1)
            throw new IllegalArgumentException("Value must be between 1 and " + dimension);

        try {
            board[row][column] = value;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Row and column must be between 0 and " + (dimension-1));
        }
    }

    public int get(int row, int column){
        try {
            return board[row][column];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Row and column must be between 0 and " + (dimension-1));
        }
    }

    public int[][] getBoard() {
        return board;
    }

    @Override
    public String toString() {
        String s = "";

        for (int i = 0; i < dimension; i++) {
            int count = 0;
            for (int j = 0; j < dimension; j++) {
                s += board[i][j] + "\t";
                count += 7;
                if (j% quadrantWidth == quadrantWidth-1 && j!= dimension-1) {
                    s += "|";
                    count++;
                }
            }
            s += "\n";

            if( i% quadrantHeight == quadrantHeight-1 && i!= dimension-1){
                for (int k = 0; k < count; k++) {
                s += "-";
                }
                s += "\n";
            }
            
        }
        return s;
    }

    @Override
    public SudokuBoard clone() {
        SudokuBoard clone = new SudokuBoard(dimension, quadrantHeight, quadrantWidth);

        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++) 
                clone.getBoard()[i][j] = board[i][j];
            
        return clone;
    }

    
}
