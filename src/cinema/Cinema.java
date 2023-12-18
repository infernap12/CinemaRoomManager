package cinema;

import java.util.Scanner;

public class Cinema {

    private static final int PREMIUM = 10;
    private static final int BUDGET = 8;
    private static seat[][] roomArray;
    private static int rows;
    private static int columns;
    private static int sales;
    private static int income;



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        columns = scanner.nextInt();

        init(columns, rows);

        int result;
        do {
            System.out.println("""
                    
                    1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit""");
            result = scanner.nextInt();
            switch (result) {
                case 1 -> {
                    System.out.println();
                    drawRoom();
                }
                case 2 -> {
                    System.out.println();
                    bookSeat();
                }
                case 3 -> {
                    System.out.println();
                    drawStats();
                }
                case 0 -> {
                    return;
                }

            }
        } while (true);
    }

    private static void drawStats() {
        System.out.println("Number of purchased tickets: " + sales);
        System.out.println("Percentage: " + getPercentage() + "%");
        System.out.println("Current income: $" + income);
        System.out.println("Total income: $" + getIncome());
    }

    private static String getPercentage() {
        int totalSeats = (rows) * (columns);
        double percentage = (double)sales / totalSeats * 100;
        return String.format("%.2f", percentage);
    }

    private static void bookSeat() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a row number:");
            int row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int column = scanner.nextInt();
            System.out.println();
            seat seat = null;
            try {
                seat = roomArray[row-1][column-1];
            } catch (Exception e) {
                System.out.println("Wrong input!");
                continue;
            }
            if (seat.getStatus() == 'B') {
                System.out.println("That ticket has already been purchased!");
                continue;
            }
            seat.setStatus('B');
            System.out.println("Ticket price: $" + seat.getPrice());
            sales++;
            income += seat.getPrice();
            break;
        }
    }


    private static void drawRoom() {
        System.out.println("Cinema:");
        char[][] printArray = new char[(rows + 1)][(columns + 1)];
        printArray[0][0] = ' ';
        for (int j = 1; j < printArray.length; j++) {
            printArray[j][0] = (char) ('0' + j);
        }
        for (int i = 1; i < printArray[0].length; i++) {
            printArray[0][i] = (char) ('0' + i);

        }
        for (int i = 0; i < roomArray.length; i++) {
            for (int j = 0; j < roomArray[i].length; j++) {
                printArray[i+1][j+1] = roomArray[i][j].getStatus();
            }
        }

        for (int j = 0; j < printArray.length; j++) {
            for (int i = 0; i < printArray[j].length; i++) {
                System.out.print(printArray[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    private static void init(int columns, int rows) {
        roomArray = new seat[rows][columns];
        int price;

        for (int j = 0; j < roomArray.length; j++) {
            for (int i = 0; i < roomArray[0].length; i++) {
                price = rows * columns > 60 && j >= roomArray.length / 2 ? BUDGET : PREMIUM;
                roomArray[j][i] = new seat(price, 'S');
            }

        }
    }

    private static int getIncome() {
        int income;
        if (rows * columns > 60) {
            income = (rows / 2) * columns * PREMIUM;
            income += (rows - (rows / 2)) * columns * BUDGET;
        } else {
            income = rows * columns * PREMIUM;
        }
        return income;
    }

}

class seat {
    private int price;
    private char status;

    //constructor
    public seat(int price, char status) {
        this.price = price;
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

}


