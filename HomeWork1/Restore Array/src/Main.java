/**
 * Restore array
 * Given an unsorted array of positive integers. The array divided into monotone parts in which some elements
 * replaced by negative values. Find missing positive integers in the array. Negative elements can be placed only
 * inside a monotone part. Negative elements cannot be in neighboring cells.
 * *monotone part - a sequence where each next number greater(less) than previous. e.g. 4 5 6 7 8 is the monotone part,
 * but 5 3 1 6 7 is not monotone.
 * Input
 * {array_length}
 * {array_values}
 * Output
 * {restored array}
 * Example
 * Input
 * 8
 * 1 2 -1 4 7 6 -2 4
 * Output
 * 1 2 3 4 7 6 5 4
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
                if (arrLength > 2) {
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

        // Checking of 4 conditions, when the array doesn't need to be fixed or cannot be fixed:
        // - First or last array element is a negative number;
        if (array[0] < 0 || array[array.length - 1] < 0) {
            System.out.println();
            System.out.println("First or last array element is a negative number, this array cannot be fixed!");
            System.exit(0);
        }

        // - No negative numbers in the array;
        for (int i = 1; i < array.length - 1; i++) {
            if (array[i] >= 0 && i == array.length - 2) {
                System.out.println("No negative numbers in the array, nothing to fix!");
                System.exit(0);
            } else if (array[i] < 0) {
                break;
            }
        }

        // - There are 2 or more consecutive negative numbers in the array.
        // - There is a negative number inside of a non-monotone part of the array;
        // Fixing array if possible.
        for (int i = 1; i < array.length - 1; i++) {
            if (array[i] < 0 && array[i + 1] < 0) {
                System.out.println("There are 2 or more consecutive negative numbers in the array, it cannot be fixed!");
                System.exit(0);
            } else if ( (array[i] < 0 && (array[i - 1] + 1 != array[i + 1] - 1) ) &&
                    (array[i] < 0 && (array[i - 1] - 1 != array[i + 1] + 1)) ) {
                System.out.println("There is a negative number inside of a non-monotone part of the array!");
                System.out.println("Wrong source data were provided.");
                System.exit(0);
            } else if (array[i] < 0) {
                array[i] = (array[i - 1] + array[i + 1]) / 2;
            }
        }

        // Restored array output
        System.out.println();
        System.out.print("Restored array: ");
        for (int i : array) {
            System.out.print(i + " ");
        }
    }
}
