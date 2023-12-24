import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Display welcome message
        System.out.println("---- MAYIN TARLASI OYUNU ----");
        Scanner input = new Scanner(System.in);

        // Prompt user for the number of rows
        System.out.print("Satır Sayısı Giriniz : ");
        while (!input.hasNextInt()) {
            // Checking that the user entered a number
            System.out.println("Bir Sayı Girmelisin !");
            input.next();
        }
        int row = input.nextInt();

        // Row numbers must be at least two
        while (row < 2) {
            // Checking whether the user entered a number greater than 2
            System.out.println("2 veya daha büyük bir sayı girmelisin !");
            row = input.nextInt();
        }

        // Prompt user for the number of columns
        System.out.print("Sütun Sayısı Giriniz : ");
        while (!input.hasNextInt()) {
            // Checking that the user entered a number
            System.out.println("Bir Sayı Girmelisin !");
            input.next();
        }
        int col = input.nextInt();
        // Ensure that the number of columns is at least 2
        while (col < 2) {
            // Checking whether the user entered a number greater than 2
            System.out.print("2 veya daha büyük bir sayı girmelisin !");
            col = input.nextInt();
        }


        MineSweeper mine = new MineSweeper(row, col);   // Creating a minesweeper class && get parameters
        mine.placeMines();             // Randomly place mines on the game board

        while (!mine.gameOver) {
            System.out.print("Satır Koordinatı : ");
            while (!input.hasNextInt()) {
                System.out.print("Bir Sayı Girmelisin !");
                input.next();
            }
            int coordX = input.nextInt();

            while (coordX > row || coordX < 0) {
                System.out.print("X Koordinatı Satır Değerinden Küçük Olamaz!");
                coordX = input.nextInt();
            }

            System.out.print("Sütun Koordinatı : ");
            while (!input.hasNextInt()) {
                System.out.println("Bir Sayı Girmelisin !");
                input.next();
            }
            int coordY = input.nextInt();

                // Ensure that the column coordinate is within the valid range
            while (coordY > col || coordY < 0) {
                // Input validation: Ensure the user enters a valid column coordinate
                System.out.print("Y Koordinatı Sütun Değerinden Küçük Olamaz!");
                coordY = input.nextInt();
            }

            // Run the game with the user-input coordinates
            mine.run(coordX, coordY);
        }
    }
}
