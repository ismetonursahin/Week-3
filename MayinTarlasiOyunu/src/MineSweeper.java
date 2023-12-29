import java.util.*;

public class MineSweeper {

    Scanner scan = new Scanner(System.in);
    int row;
    int col;
    String[][] userBoard; // User's game board
    String[] mineList;   // Array to store the locations of mines
    String[] inputList;   // Array to store of inputs
    String givenCoord;    // User input coordinates
    int size;
    int givenRow;          // User input row
    int givenCol;          // User input column
    boolean gameOver = false;    // Flag to indicate whether the game is over
    int openedCellsCount = 0;

    MineSweeper(int row, int col) {
        this.row = row;
        this.col = col;
        this.userBoard = new String[row][col];
        this.size = row * col;
        this.inputList = new String[size];
    }

    // Method to randomly place mines
    public void placeMines() {
        int randomRow, randomCol, count = 0;
        mineList = new String[size / 4];                   // Değerlendirme Formu - 8
        while (count != (size / 4)) {
            randomRow = (int) (Math.random() * row);
            randomCol = (int) (Math.random() * col);
            String randomMine = randomRow + "," + randomCol;
            boolean isMatch = false; // Check if the generated mine is already added
            for (int i = 0; i < mineList.length; i++) {
                if (mineList[i] != null && mineList[i].equals(randomMine)) {
                    isMatch = true;
                    break;
                }
            }
            if (isMatch) {
                continue;
            }
            mineList[count] = randomRow + "," + randomCol;
            count++;
        }
       // System.out.println(Arrays.toString(mineList) + " MINES ARE PLACED IN THESE AREAS");
    }

    // Method to draw the user's board
    public void draw() {
        if (checkCoordInMineList(givenCoord)) {
            System.out.println("GAME OVER !");  // Değerlendirme - 15
            gameOver = true;                    // Değerlendirme  - 13
            return;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if ((i + "," + j).equals(givenCoord)) {
                    userBoard[i][j] = checkValueofCoord();   //Değerlendirme -12
                } else if (userBoard[i][j] == null) {
                    userBoard[i][j] = "-1";
                } else if (userBoard[i][j].equals("-")) {
                    userBoard[i][j] = "-";
                } else if (Integer.parseInt(userBoard[i][j]) == -1) {
                    userBoard[i][j] = "-";
                }
                System.out.print(userBoard[i][j].equals("-1") ? "- " : userBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Method to check if coordinates are in the list of mines
    public boolean checkCoordInInputList() {
        boolean isMatch = false;
       // System.out.println(givenCoord);
        for (int i = 0; i < openedCellsCount; i++) {
            if (inputList[i].equals(givenCoord)) {
                return !isMatch;
            }
        }
        inputList[openedCellsCount] = givenCoord;
        return isMatch;
    }

    // Method to check if coordinates are in the list of mines
    public boolean checkCoordInMineList(String coord) {
        boolean isMatch = false;
        for (int i = 0; i < mineList.length; i++) {
            if (mineList[i].equals(coord)) {
                return !isMatch;
            }
        }
        return isMatch;
    }

    // Method to check the number of mines around a coordinate
    public String checkValueofCoord() {
        int count = 0;
        for (int i = (givenRow - 1); i <= (givenRow + 1); i++) {
            for (int j = (givenCol - 1); j <= (givenCol + 1); j++) {
                if (i < 0 || j < 0 || i >= row || j >= col) {
                    continue;
                }
                if (checkCoordInMineList(i + "," + j)) {
                    count++;
                }
            }
        }
        return Objects.toString(count);
    }

    public void incrementOpenedCellsCount() {
        openedCellsCount++;
    }

    // Checking if the game is won or not
    public boolean checkGameWon() {
        // If all the cells are opened and the game is not over, the game is won.
        return openedCellsCount + 1 == (row * col - mineList.length) && !gameOver;  // Değerlendirme - 14
    }

    // Method to start the game
    public void run(int coordX, int coordY) {

        givenRow = coordX;
        givenCol = coordY;
        givenCoord = coordX + "," + coordY;

        if (checkCoordInInputList()) {
            System.out.println("USED COORDS, PLS SELECT A NEW ONE!");
            return;
        }
        // Check if the game is won
        if (checkGameWon()) {
            System.out.println("CONGRATULATIONS! YOU WON!"); // Değerlendirme - 15
            gameOver = true;
        } else {
            // If the game is not won, the game continues
            draw();
            incrementOpenedCellsCount();   // The counter is incremented when the cell is opened
        }
    }
}
