import java.util.Scanner;

// Takes in a number and displays the first 15 times tables of that number.
// It takes in a second number, and multiplies the previous results by the second number
// It then displays these results

public class GoodCode {
    public static void main (String[] args) {
        // All non-throwaway variables defined at the start
        Scanner scannerObject = new Scanner(System.in);

        int chosenTimesTable;
        int secondChosenTimesTable;
        int numOfMultiples = 15;

        int[] firstMultiples = new int[numOfMultiples];

        
        System.out.println("Please choose a number: ");

        chosenTimesTable = Integer.parseInt( scannerObject.nextLine() );

        // First set of times tables stored in the array and then printed
        for (int i = 0; i < numOfMultiples; i++ ) {
            firstMultiples[i] = chosenTimesTable * (i+1);

            System.out.println(chosenTimesTable + " x " + (i+1) + " = " + firstMultiples[i]);
        }
        
        System.out.println("\nPlease choose another number to multiply the above results: ");

        secondChosenTimesTable = Integer.parseInt( scannerObject.nextLine() );

        // The stored numbers in the array now multiplied by the 2nd input
        for (int i = 0; i < numOfMultiples; i++ ) {
            firstMultiples[i] = firstMultiples[i] * secondChosenTimesTable;

            System.out.println(firstMultiples[i] + " x " + secondChosenTimesTable + " = " + firstMultiples[i]);
        }

        scannerObject.close();

    }

}