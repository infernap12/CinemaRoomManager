package cinema;

import java.util.Scanner;

public class Cinema {

    private static seat[][] roomArray;
    private static int rows;
    private static int columns;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        columns = scanner.nextInt();
        System.out.println();

        init(columns, rows);
        drawRoom();
        bookSeat(scanner);
        drawRoom();

//        System.out.println("Total income:");
//        System.out.println("$" + getIncome(rows, columns));
    }

    private static void bookSeat(Scanner scanner) {
        System.out.println("Enter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int column = scanner.nextInt();
        System.out.println();

        seat seat = roomArray[row-1][column-1];
        seat.setStatus('B');
        System.out.println("Ticket price: $" + seat.getPrice());
        System.out.println();
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


    private static void init(int coloumns, int rows) {
        roomArray = new seat[rows][coloumns];
        final int PREMIUM = 10;
        final int BUDGET = 8;
        int price;

        for (int j = 0; j < roomArray.length; j++) {
            for (int i = 0; i < roomArray[0].length; i++) {
                price = rows * coloumns > 60 && j >= roomArray.length / 2 ? BUDGET : PREMIUM;
                roomArray[j][i] = new seat(price, 'S');
            }

        }
    }

    private static int getIncome(int rows, int columns) {
        int income;
        final int PREMIUM = 10;
        final int BUDGET = 8;
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

/*
room profit
if seats < 60 $10 a seat
if seats more than 60
row / 2 = rows that are $10 seat the other rows are $8 a seat
how to handle grabbing other rows?
row count - (row / 2) == cheap row count?

git this stuff

key value pairs inside 2d array?
3d array with static positions?
ticket price, occupied status
 */


