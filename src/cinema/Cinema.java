package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        System.out.println(getRoomString());
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int columns = scanner.nextInt();
        int[][] roomArray = new int[columns][rows];
        System.out.println("Total income:");
        System.out.println("$" + getIncome(rows,columns));
    }

    private static int getIncome(int rows, int columns) {
        int income = 0;
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

    private static String getRoomString() {
        String roomString = """
                Cinema:
                  1 2 3 4 5 6 7 8
                1 S S S S S S S S
                2 S S S S S S S S
                3 S S S S S S S S
                4 S S S S S S S S
                5 S S S S S S S S
                6 S S S S S S S S
                7 S S S S S S S S""";
        return roomString;
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


