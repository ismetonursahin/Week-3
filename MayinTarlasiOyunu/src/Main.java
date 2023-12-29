import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Display welcome message
        System.out.println("---- MINE SWEEPER GAME ----");
        Scanner input = new Scanner(System.in);

        // Prompt user for the number of rows
        System.out.print("Enter Number of Row: ");                 // Değerlendirme Formu-7
        while (!input.hasNextInt()) {
            // Checking that the user entered a number
            System.out.println("You must a number!");
            input.next();
        }
        int row = input.nextInt();

        // Row numbers must be at least two
        while (row < 2) {
            // Checking whether the user entered a number greater than 2
            System.out.println("You must enter a number 2 or greater");
            row = input.nextInt();
        }

        // Prompt user for the number of columns
        System.out.print("Enter Number of Column: ");
        while (!input.hasNextInt()) {
            // Checking that the user entered a number
            System.out.println("You must a number!");
            input.next();
        }
        int col = input.nextInt();
        // Ensure that the number of columns is at least 2
        while (col < 2) {
            // Checking whether the user entered a number greater than 2
            System.out.print("You must enter a number 2 or greater");
            col = input.nextInt();
        }


        MineSweeper mine = new MineSweeper(row, col);   // Creating a minesweeper class && get parameters
        mine.placeMines();             // Randomly place mines on the game board


        while (!mine.gameOver) {
            System.out.print("Enter a row coordinate : ");
            while (!input.hasNextInt()) {
                System.out.print("You must a number!");        // Değerlendirme Formu -9
                input.next();
            }
            int coordX = input.nextInt();

            while (coordX > row || coordX < 0) {
                System.out.print("Coordinate cannot be less than Number of Rows");   //Değerlendirme - 10
                coordX = input.nextInt();
            }

            System.out.print("Enter a column coordinate : ");
            while (!input.hasNextInt()) {
                System.out.println("You must a number!");
                input.next();
            }
            int coordY = input.nextInt();

                // Ensure that the column coordinate is within the valid range
            while (coordY > col || coordY < 0) {
                // Input validation: Ensure the user enters a valid column coordinate
                System.out.print("Coordinate cannot be less than Number of columns");
                coordY = input.nextInt();
            }

            // Run the game with the user-input coordinates
            mine.run(coordX, coordY);
        }
    }
}
