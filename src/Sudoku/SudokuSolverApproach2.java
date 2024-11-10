package Sudoku;

public class SudokuSolverApproach2 {

    //Code with Sachin

    private static final int GRID_SIZE = 9;
    public static void main(String[] args) {

        int[][] board = {
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };

        System.out.println("Welcome to Sudoku Solver Program");

        printBoard(board);
        if (solveBoard(board)) {
            System.out.println("Successfully solved the board");
        } else {
            System.out.println("Unsolvable board :(");
        }
        System.out.println("Board after solving--");
        printBoard(board);
    }


    private static void printBoard(int[][] board) {
        for (int row=0; row<board.length; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int column=0; column<board.length; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    //method to check if that number already exists in the row
    private static boolean numberExistInRow (int[][] board, int number, int row) {
        for (int i=0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean numberExistInColumn (int[][] board, int number, int column) {
        for (int i=0; i < GRID_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    // check if number exists in the 3x3 grid
    private static boolean numberExistInGrid (int[][] board, int number, int row, int column) {
        int localBoxRow = row - (row % 3);
        int localBoxCol = column - (column % 3);

        for (int i= localBoxRow; i< localBoxRow+3; i++) {
            for (int j= localBoxCol; j< localBoxCol+3; j++) {
                if(board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    //Lets write one method that checks all 3 of those above conditions
    private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !numberExistInRow(board,number,row) &&
                !numberExistInColumn(board,number,column) &&
                !numberExistInGrid(board,number,row,column);
    }

    //real method/algorithm which does all recursion & backtracking
    private static boolean solveBoard(int[][] board) {
        for (int row=0; row < GRID_SIZE; row++) {
            for (int column=0; column < GRID_SIZE; column++) {
                if (board[row][column] == 0) {
                    //try all numbers from 1 to 9..
                    for (int numberToTry=1; numberToTry <= GRID_SIZE; numberToTry++) {
                        if (isValidPlacement(board,numberToTry,row,column)) {
                            board[row][column] = numberToTry;

                            //here is where recursion of algo comes in
                            if(solveBoard(board)){
                                return true;
                            } else {
                                // clear the placement of number we tried and put 0 back again (signifies empty)
                                board[row][column] = 0;
                            }
                        }
                    }
                    //none of the number is valid placement then return false -> i.e I can't solve this board
                    return false;
                }
            }
        }
        return true;
    }

}
