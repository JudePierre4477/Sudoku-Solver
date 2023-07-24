import java.util.Scanner;

public class SudokuSolver {
    // Static variable so that the grid size can be updated with just changing this value.
    static int GRIDSIZE = 9; 

    //Ok methods to return the boolean value checked by the rowCheck, columnChck and boxCheck methods.
    public static boolean isOk(int [][] board, int num, int row, int column){
        if(rowChecker(board, num, row) && columnChecker(board, num, column) && boxChecker(board, num, column, row)){
            return true;
        }
        return false;
    }
    //Row check method to check if the number is in the same row of the board.
    public static boolean rowChecker(int[][] board, int num, int row){
        for(int i = 0; i < GRIDSIZE; i++){
            if(board[row][i] == num)
            return false;
        }
        return true;
    }
    //Column check method to check if the number is in the same column of the board.
    public static boolean columnChecker(int[][] board, int num, int column){
        for(int i=0; i < GRIDSIZE; i++){
            if(board[i][column] == num)
            return false;
        }
        return true;
    }
    //Box check method to check if the number is already in the box that we are currently in.
    public static boolean boxChecker(int[][] board, int num, int col, int row){
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;

        for(int i = boxRow; i < boxRow+3; i++){
            for(int j = boxCol; j < boxCol+3; j++){
                if(board[i][j]==num){
                    return false;
                }
            }
        }
        return true;
    }
    //Solve board method that runs 2 for loops to traverse the entire board and a conditional that check's for any empty location, then runs another for loop checking values 1-9 to see if they will work in that position.
    public static boolean solveBoard(int [] [] board){
        for(int row = 0; row < GRIDSIZE; row++){
            for(int col = 0; col < GRIDSIZE; col++){
                if(board[row][col]==0){
                    for(int tryNum = 1; tryNum <= GRIDSIZE; tryNum++){
                        if(isOk(board, tryNum, row, col)){
                            board[row][col] = tryNum;
                            if(solveBoard(board)){
                                return true;
                            }else{
                                board[row][col] = 0;
                            }
                        }
                    }
                         return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args){
        // Board declaration.
         int [][] board = {
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
        //Passing the board to the method to solve it, print the solution, and "Solved".
        if(solveBoard(board)){
            System.out.println("Solved");
            for(int [] row: board){
                for(int element: row){
                    System.out.print(element + " ");
                }
                System.out.println();
            }
        }
        //If the board cannot be solved then there might be a problem with the board.
        else{
            System.out.println("Unsolvable");
        }
    }
}
