package Sudoku;

public class SudokuMain {

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };


        System.out.println("Welcome to Sudoku Solver!");

        if (validSudokuSolution(board, 0, 0)) {
            System.out.println("Successfully solved the board");
        } else {
            System.out.println("Unsolvable board :(");
        }

        printBoard(board);

//        System.out.println(validSudokuSolution(board, 0, 0));
        }


        public static void printBoard(char[][] board) {
            for (int i=0; i<board.length; i++) {
                for (int j=0; j<board.length; j++) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
        }

        //recursive function which will return boolean
    public static boolean validSudokuSolution(char[][] board, int row, int column) {
        //base condition
        if (row==board.length) {
            return true;
        }

        //from next time, what will be out new row and column?
        int newRow = 0;
        int newColumn = 0;
        if (column != board.length - 1) {
            newRow = row;
            newColumn = column + 1;
        } else {
            newRow = row + 1;
            newColumn = 0;
        }
        // if already number is written then move to next cell. OR else check to set one of nos from 1 to 9 numbers
        if (board[row][column] != '.') {
            if (validSudokuSolution(board, newRow, newColumn)) {
                return true;    // we have got the solution
            }
        } else {
            for (int i = 1; i<=board.length; i++) {
                if (isSafe(board, row, column, i)) {
                    board[row][column] = (char) (i+'0');
                    if (validSudokuSolution(board, newRow, newColumn)) {
                        return true;
                    } else {
                        board[row][column] = '.';
                    }
                }
            }
        }
        return false;
    }

    private static boolean isSafe(char[][] board, int row, int column, int number) {
        //to verify if it is safe to place the number or NOT
        for (int i =0; i<board.length; i++) {
            if (board[i][column] == (char)(number + '0')) {
                return false;
            }
            if (board[row][i] == (char)(number + '0')) {
                return false;
            }
        }

        //grid
        int startRow = (row/3) * 3;
        int startColumn = (column/3) * 3;

        for (int i = row; i < startRow+3; i++) {
            for (int j = column; j < startColumn+3; j++) {
                if (board[i][j] == (char)(number + '0')) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void solveSudoku(char[][] board) {
        validSudokuSolution(board, 0, 0);

    }
}