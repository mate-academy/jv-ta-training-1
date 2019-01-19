/**
 * 3. Ranges
 * Given a sorted integer array. Print all ranges in which each next element is increased by one.
 * The range is defined its minimal and maximal values. If the range contains one element print only its value.
 * Input
 * {array_length}
 * {array_values}
 * Output
 * [{min_range1} {max_range1}]...[{min_rangeN} {max_rangeN}]
 * Example
 * Input
 * 11
 * 1 2 3 5 8 9 10 13 14 15 16
 * Output
 * [1 3][5][8 10][13 16]
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int arrLength;

        // Verified array length input
        while (true) {
            System.out.print("Please enter array length: ");
            if (input.hasNextInt()) {
                arrLength = input.nextInt();
                if (arrLength > 1) {
                    break;
                } else {
                    System.out.println("Incorrect input!");
                    System.out.println();
                }
            } else {
                System.out.println("Incorrect input!");
                System.out.println();
                input.nextLine();
            }
        }

        int[] array = new int [arrLength];

        // Verified array elements input
        for (int i = 0; i < array.length; ) {
            while (true) {
                System.out.print("Please enter array element [" + (i) + "]: ");
                if (input.hasNextInt()) {
                    array[i] = input.nextInt();
                    i++;
                    break;
                } else {
                    System.out.println("Incorrect input!");
                    input.nextLine();
                }
            }
        }

        // Source array output
        System.out.println();
        System.out.print("Source array: ");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Ranges output
        System.out.print("Ranges: ");
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                System.out.print("[" + array[i] + "]");
                System.exit(0);
            }
            if (i < array.length - 1) {
                if (array[i] + 1 != array[i + 1]) {
                    System.out.print("[");
                } else {
                    System.out.print("[" + array[i]);
                    while (i < array.length - 1) {
                        if (array[i] + 1 == array[i + 1]) {
                            i++;
                        } else {
                            break;
                        }
                    }
                    System.out.print(" ");
                }
            }
            System.out.print(array[i] + "]");
        }
    }
}